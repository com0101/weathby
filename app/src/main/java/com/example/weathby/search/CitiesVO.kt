package com.example.weathby.search

import com.example.weathby.home.HomeVo

sealed class CitiesVO(val id: Int)
data class CityResult(val cityId: Int, val cityName: String, val country: String) : CitiesVO(cityId)
data class EmptyState(val cityId: Int) : CitiesVO(cityId)
data class ErrorState(val cityId: Int, val message: String) : CitiesVO(cityId)
