package com.example.weathby.localDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(value = [RoomTypeConverters::class])
@Database(entities = [CityEntities::class], version = 2)
abstract class CityDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDAO

    companion object {
        private var INSTANCE: CityDatabase? = null

        fun getInstance(context: Context): CityDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    CityDatabase::class.java,
                    "city.db"
                ).build()
            }.also { INSTANCE = it }
        }
    }
}