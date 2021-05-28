package com.example.nassweather.model
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

@Entity(tableName = "weather_table")
data class Weather(
/// we can use SerializedName to Change Variable name
    @Embedded
    val coord: coord,
    val weather: List<MyWeather>,
    @PrimaryKey
    val base: String,
    @Embedded
    val main: Main,
    val visibility: Int,
    @Embedded
    val wind: wind,
    @Embedded
    val clouds: clouds,
    val dt: Int,
    @Embedded
    val sys: sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int

)

data class coord(
    val lon: Double,
    val lat: Double
)

data class MyWeather(
    val main: String,
    val description: String,
    val icon: String
)
class WeatherTypeConvertor{
    @TypeConverter
    fun weatherToGson(value:List<MyWeather>?)= Gson().toJson(value)
    @TypeConverter
    fun jsonToWeather(value:String)=Gson().fromJson(value,Array<MyWeather>::class.java).toList()
}

data class Main(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val humidity: Int
)

data class wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class clouds(
    val all: Int
)

data class sys(
    val type: Int,

    val country: String,
    val sunrise: Int,
    val sunset: Int
)