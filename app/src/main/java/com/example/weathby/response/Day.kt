package com.example.weathby.response

import com.squareup.moshi.Json

data class Day(
    @Json(name = "maxtemp_c") val maxtemp_c: Float,
    @Json(name = "mintemp_c") val mintemp_c: Float,
    @Json(name = "avgtemp_c") val avgtemp_c: Float,
    @Json(name = "maxwind_kph") val maxwind_kph: Float,
    @Json(name = "avgvis_km") val avgvis_km: Float,
    @Json(name = "avghumidity") val avghumidity: Float,
    @Json(name = "daily_chance_of_rain") val daily_chance_of_rain: Int,
    @Json(name = "condition") val condition: ForecastCondition,
    @Json(name = "uv") val uv: Float
)
