package com.example.weathby

import android.content.Context
import com.example.weathby.localDataBase.CityDAO
import com.example.weathby.localDataBase.CityDatabase
import com.example.weathby.localDataBase.CityEntities
import com.example.weathby.network.WeathbyRetrofit
import com.example.weathby.network.WeathbyService
import com.example.weathby.response.ForecastResponse
import com.example.weathby.response.SearchResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val db: CityDAO,
    private val api: WeathbyService
) {

    suspend fun getForecast(location: String): Response<ForecastResponse> {
        return api.getForecast(query = location)
    }

    suspend fun getForecastSearch(input: String): Response<SearchResponse> {
        return api.getForecastSearch(query = input)
    }

    suspend fun getDB() : List<CityEntities> {
        return db.getAll()
    }

    suspend fun insertDB(city: CityEntities) {
        return db.insertAll(city)
    }
}