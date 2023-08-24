package com.example.weathby

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class WeatherApplication : Application() {
    companion object {
        var instance: WeatherApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}