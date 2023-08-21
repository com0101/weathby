package com.example.weathby.response

import com.squareup.moshi.Json

data class Search(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "region") val region: String,
    @Json(name = "country") val country: String,
    @Json(name = "lat") val lat: Float,
    @Json(name = "lon") val lon: Float,
    @Json(name = "url") val url: String
)
