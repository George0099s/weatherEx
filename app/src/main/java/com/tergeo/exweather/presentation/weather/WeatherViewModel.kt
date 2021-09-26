package com.tergeo.exweather.presentation.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tergeo.exweather.data.network.entities.WeatherResponse
import com.tergeo.exweather.domain.repository.Repository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : ViewModel() {

    private val repository = Repository()
    val weatherLiveData = MutableLiveData<WeatherResponse>()
    private val disposable = CompositeDisposable()

    fun getWeather() {
        disposable.add(
            repository.getDataService("moscow")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weatherLiveData.value = it
                }, {
                    Log.d(TAG, "getWeather: $it")
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    companion object {

        private const val TAG = "WeatherViewModel"
    }
}