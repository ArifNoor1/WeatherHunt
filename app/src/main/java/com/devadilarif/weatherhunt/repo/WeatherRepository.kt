package com.devadilarif.weatherhunt.repo

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.devadilarif.weatherhunt.LocationManager
import com.devadilarif.weatherhunt.callbacks.LocationListeners
import com.devadilarif.weatherhunt.repo.local.WeatherHuntDatabase
import com.devadilarif.weatherhunt.repo.local.model.Forcast
import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse
import com.devadilarif.weatherhunt.repo.remote.Networking
import com.devadilarif.weatherhunt.repo.remote.Networking.WEATHER_BASE_URL
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import java.lang.NullPointerException

class WeatherRepository(private val context : Context) : LocationListeners {
    /*
        1. Make API Request to forecast endpoint
        2. Store the response in ROOM by insertForecast(DailyForecast)
     */

    private lateinit var locationManager : LocationManager
    private var disposables : CompositeDisposable


//    private lateinit var lastLocation : Location

    init {
        locationManager = LocationManager(context)
        locationManager.addListener(this)
        disposables = CompositeDisposable()
    }

    //TODO: Add caching mechanism for API
    fun requestForecasts(location: Location) {
      disposables.add(  Networking.create(WEATHER_BASE_URL, File(""),1024)
            .queryWeeklyForcast(location.latitude, location.longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                val forecastDao = WeatherHuntDatabase.getInstance(context).forecastDao()
                it.daily.forEach{forcast->
                    forecastDao.insertForecast(forcast)
                }
            },{
                Timber.e("$it")
            })
      )
    }

    //return ROOM Query
//    fun getForecasts(): LiveData<List<Forcast>> {
//
//    }

    /*
        1. Make API Request to current weather endpoint
        2. Store the response in ROOM
     */
    fun requestCurrentWeather() {

    }



    //return ROOM Query
//    fun getCurrentWeather(): LiveData<WeatherResponse> {
//
//    }

    override fun onLastLocationFound(location: Location) {
//        this.lastLocation = location
        requestForecasts(location)
    }
}