package com.example.weathby.response

data class Day(
    val maxtemp_c: Float,
    val mintemp_c: Float,
    val avgtemp_c: Float,
    val maxwind_kph: Float,
    val avgvis_km: Float,
    val avghumidity: Float,
    val daily_chance_of_rain: Int,
    val condition: ForecastCondition,
    val uv: Float
)
