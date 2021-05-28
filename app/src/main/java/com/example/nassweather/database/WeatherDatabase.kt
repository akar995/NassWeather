package com.example.nassweather.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nassweather.model.MyWeather
import com.example.nassweather.model.Weather
import com.example.nassweather.model.WeatherTypeConvertor


@TypeConverters(WeatherTypeConvertor::class)
@Database(entities = [Weather::class],version = 1,exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract  fun weatherDao():WeatherDoa

    companion object{
        @Volatile
        private  var INSTANCE :WeatherDatabase? =null
        fun getDatabase(context:Context):WeatherDatabase{
            val temInstance= INSTANCE;
            if (temInstance!=null){
                return  temInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext,WeatherDatabase::class.java,"weather_database"
                ).build()
                INSTANCE=instance;
                return  instance
            }
        }

    }
}