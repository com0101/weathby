package com.example.weathby.response

data class DayForecast(
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<CurrentForecast>
)
