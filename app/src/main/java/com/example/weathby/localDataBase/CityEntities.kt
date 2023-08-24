package com.example.weathby.localDataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cityTable")
class CityEntities (
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "cityName") val cityName: String,
    @ColumnInfo(name = "week") val week: String,
    @ColumnInfo(name = "cityCurrentTemp") val currentTemp: String,
    @ColumnInfo(name = "cityCurrentWind") val currentWind: String,
    @ColumnInfo(name = "cityCurrentRain") val currentRain: String,
    @ColumnInfo(name = "cityCurrentWet") val currentWet: String,
    @ColumnInfo(name = "hourTemp") val hourTemp: tempList,
    @ColumnInfo(name = "dayTemp") val dayTemp: tempList,
)