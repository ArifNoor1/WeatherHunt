package com.devadilarif.weatherhunt.repo

import android.content.Context
import android.location.Location
import android.net.Network
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.devadilarif.weatherhunt.LocationManager
import com.devadilarif.weatherhunt.callbacks.LocationListeners
import com.devadilarif.weatherhunt.repo.local.WeatherHuntDatabase
import com.devadilarif.weatherhunt.repo.local.model.Forcast
import com.devadilarif.weatherhunt.repo.local.model.Weather
import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse
import com.devadilarif.weatherhunt.repo.remote.Networking
import com.devadilarif.weatherhunt.repo.remote.Networking.WEATHER_BASE_URL
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import java.lang.NullPointerException

class WeatherRepository(private val context : Context, private val owner : LifecycleOwner) : LocationListeners {
    private lateinit var locationManager : LocationManager
    private var disposables : CompositeDisposable
    private var db : WeatherHuntDatabase

//    private lateinit var lastLocation : Location

    init {
        locationManager = LocationManager(context)
        locationManager.addListener(this)
        disposables = CompositeDisposable()
        db = WeatherHuntDatabase.getInstance(context)
    }

    //TODO: Add caching mechanism for API
    fun requestForecasts(location: Location) {
      disposables.add(  Networking.create(WEATHER_BASE_URL, File(""),1024)
            .queryWeeklyForcast(location.latitude, location.longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                val forecastDao = db.forecastDao()
                it.daily.forEach{forcast->
                    forecastDao.insertForecast(forcast)
                }
                Timber.d("successful fetch of Forecast Weather")

            },{
                Timber.e("$it")
            })
      )
    }

    //return ROOM Query
    fun getForecasts(onSuccess : (List<Forcast>)-> Unit) {
       db.forecastDao().getWeeklyForecast().observe(owner, Observer {
           onSuccess(it)
       })
    }


    /*
        1. Make API Request to current weather endpoint
        2. Store the response in ROOM
     */
    fun requestCurrentWeather() {
        disposables.add(
            Networking.create(WEATHER_BASE_URL, File(""), 1024)
                .queryCurrentWeather("Bangalore")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    db.currentWeatherDao().insertCurrentWeather(it)
                    Timber.d("successful fetch of Current Weather")
                },{
                    Timber.e("$it")
                })
        )
    }



    //return ROOM Query
    fun getCurrentWeather(onSuccess: (WeatherResponse) -> Unit){
        db.currentWeatherDao().getCurrentWeather().observe(owner, Observer{
            onSuccess(it)
        })
    }

    override fun onLastLocationFound(location: Location) {
//        this.lastLocation = location
        requestForecasts(location)
    }

    fun onDestroy(){
        disposables.clear()
    }
}