package com.example.weathby.network

import com.example.weathby.resource.API_KEY
import com.example.weathby.response.ForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeathbyService {
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String,
        @Query("days") days: Int = 7,
        @Query("aqi") airQuality: String = "no"
    ): Response<ForecastResponse>

    @GET("search.json")
    suspend fun getForecastSearch(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ): Response<ForecastResponse>
}