package com.example.weathby.response

import com.squareup.moshi.Json

data class ForecastResponse(
    @Json(name = "location") val location: Location,
    @Json(name = "current") val current: CurrentForecast,
    @Json(name = "forecast") val forecast: Forecast
)
