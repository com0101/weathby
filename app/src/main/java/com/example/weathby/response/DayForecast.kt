package com.example.weathby.response

import com.squareup.moshi.Json

data class DayForecast(
    @Json(name = "date") val date: String,
    @Json(name = "date_epoch")  val date_epoch: Int,
    @Json(name = "day") val day: Day,
    @Json(name = "hour") val hour: List<CurrentForecast>
)
