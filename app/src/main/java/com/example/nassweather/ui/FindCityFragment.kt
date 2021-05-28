package com.example.nassweather.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nassweather.MainViewModel
import com.example.nassweather.MainViewModelFactory
import com.example.nassweather.R
import com.example.nassweather.util.Helper.Companion.isOnline
import com.example.nassweather.database.WeatherDatabase
import com.example.nassweather.databinding.FragmentFindCityBinding
import com.example.nassweather.repository.Repository
import com.example.nassweather.util.Constants

class FindCityFragment : Fragment() {

    lateinit var binding: FragmentFindCityBinding
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.customBtn.setOnClickListener {

            binding.progressBar2.visibility=View.VISIBLE
            binding.textView3.visibility=View.INVISIBLE

            if(isOnline(requireContext())){
                val repository =
                    Repository(weatherDoa = WeatherDatabase.getDatabase(requireContext()).weatherDao());
                val mainViewModelFactory = MainViewModelFactory(repository);
                viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
                var sharedPreferences =context?.getSharedPreferences("Weather", Context.MODE_PRIVATE);
               sharedPreferences!!.edit().putString("city",binding.editTextTextPersonName.text.toString()).apply()

                viewModel.getCountriesFromAPIAndStore( binding.editTextTextPersonName.text.toString())
                Navigation.findNavController(view).navigate(R.id.action_findCityFragment_to_weatherFragment)

            }else{
                binding.progressBar2.visibility=View.INVISIBLE
                binding.textView3.visibility=View.VISIBLE
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
            }

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFindCityBinding.inflate(inflater, container, false)
        return binding.root
    }


}