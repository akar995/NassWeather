package com.example.nassweather

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nassweather.model.Weather
import com.example.nassweather.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Weather>> = MutableLiveData()

    fun getWeather(): LiveData<List<Weather>>? {
        Log.e("TAG","wroooor")
        return repository.getWeather()
    }

    fun getCountriesFromAPIAndStore( city: String) {
        viewModelScope.launch {
             repository.ApiCallAndPutInDb( city)
        }
    }

}