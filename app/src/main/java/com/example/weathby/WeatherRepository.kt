package com.example.weathby

import android.content.Context
import com.example.weathby.localDataBase.CityDatabase
import com.example.weathby.localDataBase.CityEntities
import com.example.weathby.network.WeathbyRetrofit
import com.example.weathby.response.ForecastResponse
import com.example.weathby.response.SearchResponse
import retrofit2.Response

class WeatherRepository() {
    private val service = WeathbyRetrofit.makeRetrofitService()



    suspend fun getForecast(location: String): Response<ForecastResponse> {
        return service.getForecast(query = location)
    }

    suspend fun getForecastSearch(input: String): Response<SearchResponse> {
        return service.getForecastSearch(query = input)
    }

    suspend fun getDB(context: Context) : List<CityEntities> {
        val db = CityDatabase.getInstance(context)
        val cityDao = db.cityDao()
        return cityDao.getAll()
    }

    suspend fun insertDB(context: Context, city: CityEntities) {
        val db = CityDatabase.getInstance(context)
        val cityDao = db.cityDao()
        return cityDao.insertAll(city)
    }

}