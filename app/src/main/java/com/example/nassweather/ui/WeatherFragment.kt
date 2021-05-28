package com.example.nassweather.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.nassweather.MainViewModel
import com.example.nassweather.MainViewModelFactory
import com.example.nassweather.R
import com.example.nassweather.database.WeatherDatabase
import com.example.nassweather.databinding.FragmentWeatherBinding
import com.example.nassweather.repository.Repository
import java.net.URL


class WeatherFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    lateinit var  binding: FragmentWeatherBinding
    var baseIconUrl="http://openweathermap.org/img/wn/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        binding= FragmentWeatherBinding.inflate(inflater,container,false)

        val repository = Repository(weatherDoa = WeatherDatabase.getDatabase(requireContext()).weatherDao());
        val mainViewModelFactory= MainViewModelFactory(repository);

        viewModel = ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)
        Log.e("tag","whyyyyyyy")
        viewModel.getWeather()?.observe(
            viewLifecycleOwner, Observer { weathers->
                Log.i("tag",weathers.toString())
//                binding.textView.text=weathers.toString()
                if(weathers.getOrNull(0)!=null) {
//                    baseIconUrl + weathers.get(0).weather.get(0).icon+"@4x.png"
                    val view:ImageView= requireView().findViewById(R.id.imageView3)
                    Glide.with(requireActivity()).load(baseIconUrl + weathers[0].weather[0].icon+"@4x.png")
                        .into(view)
                    binding.textCityName.text= weathers[0].name
                    binding.textWind.text= weathers[0].wind.speed.toString()
                    binding.textTemp.text= weathers[0].main.temp.toString()+" °"
                    binding.textFeelTemp.text= weathers[0].main.feels_like.toString()+" ° C"
                    binding.textMinTemp.text= weathers[0].main.temp_min.toString()+" ° C"
                    binding.textMaxTemp.text= weathers[0].main.temp_max.toString()+" ° C"


                }
            }
        )




        return  binding.root;

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

       return  inflater.inflate(R.menu.weather_fragment_menu,menu)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.city_menu->{
                Navigation.findNavController(requireView()).navigate(R.id.action_weatherFragment_to_findCityFragment2)


                Toast.makeText(requireContext(), "Fack you for now", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}