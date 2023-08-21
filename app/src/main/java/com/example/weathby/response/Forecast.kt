package com.example.weathby.response

import com.squareup.moshi.Json

data class Forecast(
    @Json(name = "forecastday") val forecastday: List<DayForecast>
)
