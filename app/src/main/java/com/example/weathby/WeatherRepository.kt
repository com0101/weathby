package com.example.weathby

import com.example.weathby.network.WeathbyRetrofit
import com.example.weathby.response.ForecastResponse
import com.example.weathby.response.SearchResponse
import retrofit2.Response

class WeatherRepository {
    private val service = WeathbyRetrofit.makeRetrofitService()

    suspend fun getForecast(location: String): Response<ForecastResponse> {
        return service.getForecast(query = location)
    }

    suspend fun getForecastSearch(input: String): Response<SearchResponse> {
        return service.getForecastSearch(query = input)
    }
}