package com.devadilarif.weatherhunt.repo.remote

import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("weather")
    fun queryCurrentWeather(
        @Query("q") cityName : String,
        @Query("appid") appId : String =
    ): Single<WeatherResponse>


}