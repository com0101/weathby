package com.example.weathby.di

import android.app.Application
import com.example.weathby.localDataBase.CityDAO
import com.example.weathby.localDataBase.CityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDB(app: Application): CityDatabase = CityDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideCityDao(db: CityDatabase): CityDAO = db.cityDao()

}