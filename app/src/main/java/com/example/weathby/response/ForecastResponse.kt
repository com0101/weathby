package com.example.weathby.response

data class ForecastResponse(
    val location: Location,
    val current: CurrentForecast,
    val forecast: Forecast
)
