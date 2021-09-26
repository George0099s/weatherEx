package com.tergeo.exweather.presentation.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tergeo.exweather.R
import kotlinx.android.synthetic.main.weather_fragment.weatherText

class WeatherFragment : Fragment() {

    companion object {

        fun newInstance() = WeatherFragment()
    }

    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        viewModel.weatherLiveData.observe(viewLifecycleOwner, {
            weatherText.text = it.toString()
        })

        viewModel.getWeather()
    }
}