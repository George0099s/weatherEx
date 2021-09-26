package com.tergeo.exweather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tergeo.exweather.R
import com.tergeo.exweather.R.layout
import com.tergeo.exweather.presentation.weather.WeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, WeatherFragment())
            .commit()
    }
}