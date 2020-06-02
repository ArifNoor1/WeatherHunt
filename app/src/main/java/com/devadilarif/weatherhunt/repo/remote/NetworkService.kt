package com.devadilarif.weatherhunt.repo.remote

import com.devadilarif.weatherhunt.repo.local.model.Covid19Response
import com.devadilarif.weatherhunt.repo.local.model.ForcastResponse
import com.devadilarif.weatherhunt.repo.local.model.NewsResponse
import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

//TODO: Refactor the API KEY place as well as the constants in thq query annotation
interface NetworkService {

    @GET(EndPoints.CURRENT_WEATHER_END_POINT)
    fun queryCurrentWeather(
        @Query("q") cityName : String,
        @Query("appid") appId : String = EndPoints.WEATHER_API_KEY
    ): Single<WeatherResponse>

    @GET(EndPoints.FORCAST_ENDPOINT)
    fun queryWeeklyForcast(
        @Query("lat") latitude : Double,
        @Query("lon") longitude : Double,
        @Query("exclude") excluded : String = "hourly,current",
        @Query("appid") appId : String = EndPoints.WEATHER_API_KEY
    ) : Single<ForcastResponse>

    @GET(EndPoints.NEWS_ENDPOINT)
    fun queryTopHeadlines(
        @Query("country") country : String,
        @Query("category") category : String,
        @Query("apiKey") apiKey : String = EndPoints.NEWS_API_KEY
    ) : Single<NewsResponse>

    @GET(EndPoints.COVID_19)
    fun queryCovid19(): Single<Covid19Response>


}