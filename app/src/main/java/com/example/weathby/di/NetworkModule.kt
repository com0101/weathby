package com.example.weathby.di

import com.example.weathby.network.WeathbyRetrofit
import com.example.weathby.network.WeathbyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideApi(): WeathbyService {
        return WeathbyRetrofit
            .makeRetrofitService()
            .create(WeathbyService::class.java)
    }
}