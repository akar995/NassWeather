package com.example.nassweather.api

import com.example.nassweather.model.Weather
import com.example.nassweather.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("appid")apiKey:String=API_KEY,
        @Query("q")city:String,
        @Query("units")units:String="metric"

    ): Response<Weather>



}