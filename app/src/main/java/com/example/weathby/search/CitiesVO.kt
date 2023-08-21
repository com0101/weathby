package com.example.weathby.search

import com.example.weathby.home.HomeVo

sealed class CitiesVO
object EmptyState : CitiesVO()
data class ErrorState(val message: String) : CitiesVO()
