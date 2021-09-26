package com.tergeo.exweather.data.network

import com.tergeo.exweather.data.network.entities.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("data/2.5/weather?lat=55.669284&lon=37.6109545&APPID=a0171528e2e252834d462cae4b042e3a")
    fun getWeather(
        @Query("q") cityName: String
    ): Single<WeatherResponse>
}