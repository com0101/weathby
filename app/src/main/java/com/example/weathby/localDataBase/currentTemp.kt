package com.example.weathby.localDataBase

import com.example.weathby.home.IconType

data class currentTemp(
    val iconType: IconType,
    val time: Int,
    val tempMax: Int,
    val tempMin: Int
)
