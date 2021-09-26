package com.tergeo.exweather.domain.repository

import com.tergeo.exweather.data.network.APIService
import com.tergeo.exweather.data.network.entities.WeatherResponse
import io.reactivex.Single

class Repository {

    fun getDataService(cityName: String): Single<WeatherResponse> {
        return APIService.api.getWeather(cityName)
    }
}