package com.example.nassweather.api

import com.example.nassweather.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }
}