package com.example.nassweather.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nassweather.MainViewModel
import com.example.nassweather.MainViewModelFactory
import com.example.nassweather.R
import com.example.nassweather.util.Helper.Companion.isOnline
import com.example.nassweather.database.WeatherDatabase
import com.example.nassweather.repository.Repository
import com.example.nassweather.util.Constants


class SplashFragment : Fragment() {


    private lateinit var viewModel: MainViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository =
            Repository(weatherDoa = WeatherDatabase.getDatabase(requireContext()).weatherDao());
        val mainViewModelFactory = MainViewModelFactory(repository);
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        var sharedPreferences =context?.getSharedPreferences("Weather",Context.MODE_PRIVATE);
           val city= sharedPreferences!!.getString("city","null")
        if (city.equals("null")){

            Log.e("city",city!!)
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_findCityFragment)
            return
        }




        if(isOnline(requireContext())){
            viewModel.getCountriesFromAPIAndStore( city!!)
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_weatherFragment)
        }else{
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_weatherFragment)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }





}


