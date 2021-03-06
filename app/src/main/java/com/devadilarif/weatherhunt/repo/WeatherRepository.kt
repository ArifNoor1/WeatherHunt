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

    private var lastLocation : Location = Location("")

    init {
        lastLocation.latitude = 26.8467
        lastLocation.longitude = 80.9462
        locationManager = LocationManager(context)
        locationManager.addListener(this)
        disposables = CompositeDisposable()
        db = WeatherHuntDatabase.getInstance(context)
    }

    //TODO: Add caching mechanism for API
    fun requestForecasts() {
      disposables.add(  Networking.create(WEATHER_BASE_URL, File(""),5 * 1024 * 1024)
            .queryWeeklyForcast(lastLocation.latitude, lastLocation.longitude)
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
           if(it.isNotEmpty()){
               onSuccess(it)
           }
       })
    }



    fun requestCurrentWeather(location: Location) {
        disposables.add(
            Networking.create(WEATHER_BASE_URL, File(""), 5 * 1024 * 1024)
                .queryCurrentWeather(location.latitude,location.longitude)
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
        this.lastLocation = location
        requestForecasts()
//        requestCurrentWeather()
    }

    fun onDestroy(){
        disposables.clear()
    }
}