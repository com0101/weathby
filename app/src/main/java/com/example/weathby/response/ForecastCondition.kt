package com.example.weathby.response

import com.squareup.moshi.Json

data class ForecastCondition(
    @Json(name = "text") val text: String,
    @Json(name = "icon") val icon: String
)
