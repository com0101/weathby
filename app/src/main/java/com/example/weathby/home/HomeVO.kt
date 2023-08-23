package com.example.weathby.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class HomeVo(val id: Int)

@Parcelize
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
) : HomeVo(cityId), Parcelable

@Parcelize
data class CityDayTemp(
    val day: String,
    val icon: IconType,
    val maxTemp: String,
    val minTemp: String
): Parcelable

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
