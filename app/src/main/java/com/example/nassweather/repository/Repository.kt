package com.example.nassweather.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.nassweather.api.RetrofitInstance
import com.example.nassweather.database.WeatherDatabase
import com.example.nassweather.database.WeatherDoa
import com.example.nassweather.model.Weather
import kotlin.math.log


class Repository(private val  weatherDoa: WeatherDoa? = null) {
     fun getWeather(): LiveData<List<Weather>>? {
         val data=weatherDoa?.readWeather()
         Log.e("Repository get Weather",data.toString())
         return  data
    }



    suspend fun ApiCallAndPutInDb( city: String,){

        val response= RetrofitInstance.api.getWeather( city = city)
        if (response.isSuccessful){

            Thread(Runnable {

                weatherDoa?.deleteAll()
Log.e("Form API",response.body().toString())
                weatherDoa?.addWeather(response.body()!!)



            }).start()


        }
    }
}