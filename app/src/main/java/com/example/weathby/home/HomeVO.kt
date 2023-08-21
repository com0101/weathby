package com.example.weathby.home

sealed class HomeVo(val id: Int)
data class CityCard(
    val cityId: Int,
    val cityName: String,
    val day: String,
    val temp: String,
    val wind: String,
    val wet: String,
    val rain: String,
    val isMarked: Boolean,
    val dayTem: List<CityDayTemp>
) : HomeVo(cityId)
data class CityDayTemp(
    val day: String,
    val icon: IconType,
    val maxTemp: String,
    val minTemp: String
)
data class CityHourTemp(
    val cityId: Int,
    val time: String,
    val temp: String,
    val iconType: IconType
) : HomeVo(cityId)

data class EmptyState(val cityId: Int) : HomeVo(cityId)
data class ErrorState(val cityId: Int, val message: String) : HomeVo(cityId)
enum class IconType {
    RAIN, SUN, CLOUD
}
