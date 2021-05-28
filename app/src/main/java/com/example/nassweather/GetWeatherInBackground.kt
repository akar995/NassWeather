package com.example.nassweather

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.nassweather.api.RetrofitInstance
import com.example.nassweather.database.WeatherDatabase
import com.example.nassweather.repository.Repository
import com.example.nassweather.util.Constants.Companion.API_KEY
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GetWeatherInBackground(context: Context, workerParams: WorkerParameters) :Worker(context,
    workerParams
) {


    override fun doWork(): Result {
        Log.e("backround","working")
        var sharedPreferences =applicationContext.getSharedPreferences("Weather",Context.MODE_PRIVATE);
        val city= sharedPreferences!!.getString("city","null")
        if (city.equals("null")){

            Log.e("city",city!!)
            return  Result.failure()

        }



        val repository =
            Repository(weatherDoa = WeatherDatabase.getDatabase(applicationContext).weatherDao());

        GlobalScope.launch {
            repository.ApiCallAndPutInDb(city!!)
        }

        return Result.success();
    }
}