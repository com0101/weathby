package com.example.weathby.resource

import com.example.weathby.getDayOfWeek
import com.example.weathby.getHourFromTimeStamp
import com.example.weathby.home.CityCard
import com.example.weathby.home.CityDayTemp
import com.example.weathby.home.CityHourTemp
import com.example.weathby.home.IconType
import com.example.weathby.response.ForecastResponse
import java.util.UUID

object ReMap {

    fun setCityCard(forecast: ForecastResponse, id: UUID): CityCard {
        val cardList =
            CityCard(
                id,
                forecast.location.name,
                forecast.location.localtime_epoch.getDayOfWeek(),
                "${forecast.current.temp_c}°",
                "${forecast.current.wind_kph}m/s",
                "${forecast.current.humidity}%",
                "${forecast.current.chance_of_rain ?: 0}%",
                true,
                forecast.forecast.forecastday.map { dayForecast ->
                    CityDayTemp(
                        dayForecast.date_epoch.getDayOfWeek(),
                        when {
                            (dayForecast.hour[0].chance_of_rain ?: 0) > 50 -> IconType.RAIN
                            dayForecast.hour[0].temp_c > 27 -> IconType.SUN
                            else -> IconType.CLOUD
                        },
                        "${dayForecast.hour.maxOfOrNull { it.temp_c }}°",
                        "${dayForecast.hour.minOfOrNull { it.temp_c }}°",
                    )
                },
                forecast.forecast.forecastday[0].hour.map {
                    CityHourTemp(
                        (it.time_epoch?:0).getHourFromTimeStamp(),
                        "${it.temp_c}°",
                        when {
                            (it.chance_of_rain ?: 0) > 50 -> IconType.RAIN
                            it.temp_c > 27 -> IconType.SUN
                            else -> IconType.CLOUD
                        },
                    )
                }
            )

        return cardList
    }
}