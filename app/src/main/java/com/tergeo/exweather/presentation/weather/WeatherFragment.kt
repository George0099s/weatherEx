package com.tergeo.exweather.presentation.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.tergeo.exweather.R
import kotlinx.android.synthetic.main.weather_fragment.loading
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
            render(it)
        })

        viewModel.getWeather()
    }

    private fun render(state: WeatherScreenState){
        if (state.loading){
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }

        if (state.error != null) {
            weatherText.text = state.error.message
        }

        if (state.success != null){
            weatherText.text = state.success.toString()
        }
    }
}