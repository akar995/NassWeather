package com.example.nassweather.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nassweather.model.Weather

@Dao
interface WeatherDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWeather(weather:Weather)


    @Query("SELECT * FROM weather_table")
    fun readWeather() :LiveData<List<Weather>>

    @Query("DELETE FROM weather_table")
    fun deleteAll()

}