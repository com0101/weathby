package com.example.weathby.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

sealed class HomeVo(val id: UUID)

@Parcelize
data class CityCard(
    val cityId: UUID,
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

@Parcelize
data class CityHourTemp(
    val time: String,
    val temp: String,
    val iconType: IconType
): Parcelable

data class EmptyState(val cityId: UUID) : HomeVo(cityId)
data class ErrorState(val cityId: UUID, val message: String) : HomeVo(cityId)
enum class IconType {
    RAIN, SUN, CLOUD
}
