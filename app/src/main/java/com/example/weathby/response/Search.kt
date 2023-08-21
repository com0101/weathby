package com.example.weathby.response

data class Search(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
    val lat: Float,
    val lon: Float,
    val url: String
)
