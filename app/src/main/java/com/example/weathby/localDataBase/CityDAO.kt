package com.example.weathby.localDataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDAO {
    @Query("SELECT * FROM cityTable")
    fun getAll(): List<CityEntities>

    @Insert
    fun insertAll(vararg city: CityEntities)

    @Delete
    fun delete(city: CityEntities)
}
