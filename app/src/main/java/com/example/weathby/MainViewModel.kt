package com.example.weathby

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathby.home.CityCard
import com.example.weathby.home.CityDayTemp
import com.example.weathby.home.CityHourTemp
import com.example.weathby.home.ErrorState
import com.example.weathby.home.IconType
import com.example.weathby.localDataBase.CityEntities
import com.example.weathby.resource.ReMap.setCityCard
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

    private val _isCardInserted = MutableLiveData<Boolean>()
    val isCardInserted: LiveData<Boolean> = _isCardInserted

    private val _getCity = MutableLiveData<Resource<CityCard>>()
    val getCity: LiveData<Resource<CityCard>> = _getCity


    fun getForecast(country: String) {
        viewModelScope.launch {
            _getCity.value = Resource.Loading()
            runCatching {
                repository.getForecast(country)
            }.onSuccess {
               it.body()?.apply {
                   _getCity.value = Resource.Success(setCityCard(this))
               }
            }.onFailure {
                _getCity.value = Resource.Error(it.message ?: "連線逾時請稍後再試")
            }
        }
    }

    private fun setCardDB(context: Context, city: CityEntities) {
        viewModelScope.launch {
            runCatching {
                repository.insertDB(city)
            }.onSuccess {
                _isCardInserted.value = true
            }.onFailure {
                _isCardInserted.value = false
            }
        }
    }

}