package com.example.weathby.widget

import com.example.weathby.R
import com.example.weathby.getDayFromTimeStamp
import com.example.weathby.getMonthFromTimeStamp
import com.example.weathby.network.WeathbyRetrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.Instant

object WidgetRepository {
    private val service = WeathbyRetrofit.makeRetrofitService()

    private var _currentWeather = MutableStateFlow<WidgetInfo>(LoadingState)
    val currentWeather: StateFlow<WidgetInfo> get() = _currentWeather

    // need to change build.gradle minSdk to 26
    private var lastRun: Instant = Instant.EPOCH
    private val mutex = Mutex()

    suspend fun updateWidgetInfo() {
        // Avoid multiple widgets request weather update at once
        // put a simple timeout check1
        mutex.withLock(lastRun) {
            if (lastRun.plusSeconds(10L).isAfter(Instant.now())) {
                return
            } else {
                lastRun = Instant.now()
            }
        }
        _currentWeather.value = LoadingState
        service.getForecast(query = "London").apply {
            if (isSuccessful) {
                val forecast = body()
                _currentWeather.value = WeatherData(
                    forecast?.location?.name ?: "",
                    R.drawable.cloud,
                    "${forecast?.current?.temp_c}°",
                    "${forecast?.current?.wind_kph}m/s",
                    "${forecast?.current?.humidity}%",
                    forecast?.location?.localtime_epoch?.getMonthFromTimeStamp() ?: "0",
                    forecast?.location?.localtime_epoch?.getDayFromTimeStamp() ?: "0"
                )
            } else {
                _currentWeather.value = ErrorState(this.errorBody().toString())
            }
        }
    }
}