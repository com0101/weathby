package com.example.weathby.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherWidgetProvider: GlanceAppWidgetReceiver() {
    // 指定要提供的 widget
    override val glanceAppWidget: GlanceAppWidget = WeatherWidget()
    // 更新天氣的資訊，呼叫 repository 的 function
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
        CoroutineScope(Dispatchers.IO).launch {
            WidgetRepository.updateWidgetInfo()
        }
    }
}