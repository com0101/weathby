package com.example.weathby.widget

import android.content.Context
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.action.clickable
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ColumnScope
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentHeight
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import kotlinx.coroutines.launch

class WeatherWidget : GlanceAppWidget() {

    // if you have different size
    companion object {
        private val thinMode = DpSize(120.dp, 120.dp)
        private val smallMode = DpSize(184.dp, 184.dp)
        private val mediumMode = DpSize(260.dp, 200.dp)
        private val largeMode = DpSize(260.dp, 280.dp)
    }

    // Define the supported sizes for this widget.
    // The system will decide which one fits better based on the available space
    override val sizeMode: SizeMode = SizeMode.Responsive(
        setOf(thinMode, smallMode, mediumMode, largeMode),
    )


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.
        provideContent {
            Content()
        }
    }

    @Composable
    fun Content() {
        // Get the stored stated based on our custom state definition.
        val weatherInfo by WidgetRepository().currentWeather.collectAsState()
        // It will be one of the provided ones
        val size = LocalSize.current

        //Create the update weather on click lambda
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val updateWeather: () -> Unit = { scope.launch { refreshWeather(context) } }

        GlanceTheme {
            when (val currentWeather = weatherInfo) {
                LoadingState -> {
                    AppWidgetBox(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is WeatherData -> {
                    // Based on the size render different UI
//                    when (size) {
//                        thinMode ->  // thin UI
//                        smallMode -> // small UI
//                        mediumMode -> // medium UI
//                        largeMode -> // large UI
//                    }
                    WeatherSmall(currentWeather, updateWeather)
                }

                is ErrorState -> {
                    AppWidgetColumn(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text("Data not available")
                        Button("Refresh", updateWeather)
                    }
                }
            }
        }
    }

    @Composable
    fun WeatherSmall(weatherInfo: WeatherData, onClick: () -> Unit) {
        AppWidgetColumn(GlanceModifier.clickable(onClick)) {
            Row(
                modifier = GlanceModifier.wrapContentHeight().fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ) {
                WeatherIcon(weatherInfo, modifier = GlanceModifier.fillMaxWidth().defaultWeight())
                PlaceWeather(weatherInfo, modifier = GlanceModifier.fillMaxWidth().defaultWeight())
            }
            CurrentTemperature(weatherInfo, modifier = GlanceModifier.fillMaxSize(), Alignment.Start)
        }
    }

    @Composable
    fun CurrentTemperature(
        weatherInfo: WeatherData,
        modifier: GlanceModifier = GlanceModifier,
        horizontal: Alignment.Horizontal = Alignment.Start,
    ) {
        Column(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = horizontal,
        ) {
            val defaultWeight = GlanceModifier.wrapContentSize()
            Text(
                text = weatherInfo.temp,
                style = TextStyle(
                    fontSize = 48.sp,
                ),
                modifier = defaultWeight,
            )
            Row(modifier = defaultWeight) {
                Text(
                    text = weatherInfo.humidity,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Spacer(GlanceModifier.size(8.dp))
                Text(
                    text = weatherInfo.wind,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            }
        }
    }

    @Composable
    fun WeatherIcon(weatherInfo: WeatherData, modifier: GlanceModifier = GlanceModifier) {
        Box(modifier = modifier, contentAlignment = Alignment.TopStart) {
            Image(
                provider = ImageProvider(weatherInfo.icon),
                contentDescription = weatherInfo.temp,
                modifier = GlanceModifier.size(48.dp),
            )
        }
    }

    @Composable
    fun PlaceWeather(
        weatherInfo: WeatherData,
        modifier: GlanceModifier = GlanceModifier,
    ) {
        Column(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.End,
        ) {
            val defaultWeight = GlanceModifier.defaultWeight()
            Text(
                text = weatherInfo.placeName,
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.End,
                ),
                modifier = defaultWeight,
            )
            Text(
                text = weatherInfo.day,
                style = TextStyle(
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                ),
                modifier = defaultWeight,
            )
        }
    }

    @Composable
    fun AppWidgetBox(
        modifier: GlanceModifier = GlanceModifier,
        contentAlignment: Alignment = Alignment.TopStart,
        content: @Composable () -> Unit,
    ) {
        Box(
            modifier = appWidgetBackgroundModifier().then(modifier),
            contentAlignment = contentAlignment,
            content = content,
        )
    }

    /**
     * Provide a Column composable using the system parameters for app widgets background with rounded
     * corners and background color.
     */
    @Composable
    fun AppWidgetColumn(
        modifier: GlanceModifier = GlanceModifier,
        verticalAlignment: Alignment.Vertical = Alignment.Top,
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        content: @Composable ColumnScope.() -> Unit,
    ) {
        Column(
            modifier = appWidgetBackgroundModifier().then(modifier),
            verticalAlignment = verticalAlignment,
            horizontalAlignment = horizontalAlignment,
            content = content,
        )
    }

    @Composable
    fun appWidgetBackgroundModifier() = GlanceModifier
        .fillMaxSize()
        .padding(16.dp)
        .appWidgetBackground()
        .background(GlanceTheme.colors.background)
        .appWidgetBackgroundCornerRadius()

    private fun GlanceModifier.appWidgetBackgroundCornerRadius(): GlanceModifier {
        if (Build.VERSION.SDK_INT >= 31) {
            cornerRadius(android.R.dimen.system_app_widget_background_radius)
        } else {
            cornerRadius(16.dp)
        }
        return this
    }

    private suspend fun refreshWeather(context: Context) {
        WeatherWidget().updateAll(context)
        WidgetRepository().updateWidgetInfo()
    }

}