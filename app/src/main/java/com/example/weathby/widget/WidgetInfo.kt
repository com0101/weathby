package com.example.weathby.widget

import androidx.annotation.DrawableRes

sealed class WidgetInfo()

object LoadingState: WidgetInfo()
data class WeatherData(
    val placeName: String,
    @DrawableRes val icon: Int,
    val temp: String,
    val wind: String,
    val humidity: String,
    val day: String,
    val hour: String,
): WidgetInfo()
data class ErrorState(val message: String) : WidgetInfo()