package com.tergeo.exweather.presentation.weather

import android.os.Handler
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tergeo.exweather.data.network.entities.WeatherResponse
import com.tergeo.exweather.domain.repository.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

data class WeatherScreenState(
    val success: WeatherResponse? = null,
    val loading: Boolean = true,
    val error: Throwable? = null
)

class WeatherViewModel : ViewModel() {

    private val repository = Repository()
    val weatherLiveData = MutableLiveData(WeatherScreenState())
    private val disposable = CompositeDisposable()

    fun getWeather() {
        weatherLiveData.value = WeatherScreenState(
            loading = true
        )
        Handler().postDelayed({
            disposable.add(
                repository.getDataService("mosco")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        weatherLiveData.value = WeatherScreenState(
                            success = it,
                            loading = false,
                            error = null
                        )
                    }, {
                        weatherLiveData.value = WeatherScreenState(
                            loading = false,
                            error = it
                        )
                    })
            )
        }, 2000)
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    companion object {

        private const val TAG = "WeatherViewModel"
    }
}