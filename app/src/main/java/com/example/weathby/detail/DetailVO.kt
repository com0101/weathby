package com.example.weathby.detail

import com.example.weathby.home.IconType

sealed class DetailVO
data class DayWeather(
    val dayOfWeek: String,
    val iconType: IconType,
    val maxTem: String,
    val min: String
): DetailVO()
