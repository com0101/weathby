package com.example.weathby.response

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "data") val result: List<Search>
)