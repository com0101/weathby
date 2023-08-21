package com.example.weathby.response

data class CurrentForecast(
    val last_updated_epoch: Int ?= null,
    val time_epoch: Int ?= null,
    val time: String ?= null,
    val temp_c: Float,
    val is_day: Int,
    val condition: ForecastCondition,
    val wind_kph: Float,
    val humidity: Int,
    val feelslike_c: Float,
    val chance_of_rain: Int ?= null,
    val uv: Float
)
