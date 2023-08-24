package com.example.weathby

import android.app.Application
import kotlin.properties.Delegates

class WeatherApplication : Application() {
    companion object {
        var instance: WeatherApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}