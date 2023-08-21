package com.example.weathby.response

import com.squareup.moshi.Json

data class CurrentForecast(
    @Json(name = "last_updated_epoch") val last_updated_epoch: Int ?= null,
    @Json(name = "time_epoch") val time_epoch: Int ?= null,
    @Json(name = "time") val time: String ?= null,
    @Json(name = "temp_c") val temp_c: Float,
    @Json(name = "is_day") val is_day: Int,
    @Json(name = "condition") val condition: ForecastCondition,
    @Json(name = "wind_kph") val wind_kph: Float,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "feelslike_c") val feelslike_c: Float,
    @Json(name = "chance_of_rain") val chance_of_rain: Int ?= null,
    @Json(name = "uv") val uv: Float
)
