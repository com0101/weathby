package com.example.weathby

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathby.home.CityCard
import com.example.weathby.home.CityDayTemp
import com.example.weathby.home.CityHourTemp
import com.example.weathby.home.IconType
import com.example.weathby.localDataBase.CityEntities
import com.example.weathby.resource.Resource
import com.example.weathby.response.ForecastResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    private val _isCardInserted = MutableLiveData<Resource<Boolean>>()
    val isCardInserted: LiveData<Resource<Boolean>> = _isCardInserted

    private val _cardList = MutableLiveData<Resource<List<CityCard>>>()
    val cardList: LiveData<Resource<List<CityCard>>> = _cardList

    private val _tempList = MutableLiveData<Resource<List<CityHourTemp>>>()
    val tempList: LiveData<Resource<List<CityHourTemp>>> = _tempList

    fun getForecast(country: String) {
        viewModelScope.launch {
            _cardList.value = Resource.Loading()
            runCatching {
                repository.getForecast(country)
            }.onSuccess {
                it.body()?.apply {
                    val (card, temp) = setCityCard(this)
                    _cardList.value = Resource.Success(card)
                    _tempList.value = Resource.Success(temp)
                }
            }.onFailure {
                _isCardInserted.value = Resource.Error(it.message ?: "連線逾時請稍後再試")
            }
        }
    }

    fun setCityCard(forecast: ForecastResponse): Pair<List<CityCard>, List<CityHourTemp>> {
        val cardList = listOf(
            CityCard(
                UUID.randomUUID(),
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
                }
            )
        )
        val hourTemp = forecast.forecast.forecastday[0].hour.map {
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
        return Pair(cardList, hourTemp)
    }

    private fun setCardDB(context: Context, city: CityEntities) {
        viewModelScope.launch {
            runCatching {
                repository.insertDB(city)
            }.onSuccess {
                _isCardInserted.value = Resource.Success(true)
            }.onFailure {
                _isCardInserted.value = Resource.Error(it.message ?: "連線逾時請稍後再試")
            }
        }
    }

    fun getCardDB(context: Context) {
        _cardList.value = Resource.Loading()
        viewModelScope.launch {
            runCatching {
                repository.getDB()
            }.onSuccess {
//                _cardList.value = Resource.Success(
//                    it.map {
//                        CityCard(
//                            ...
//                        )
//                    }
//                )
            }.onFailure {
                _cardList.value = Resource.Error(it.message ?: "連線逾時請稍後再試")
            }
        }
    }
}