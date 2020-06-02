package com.devadilarif.weatherhunt.repo.local.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.text.SimpleDateFormat

data class WeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val weather: List<Weather>,
    val wind: Wind,


    var lastFetchedTime : String
){
    data class Clouds(
        val all: Int
    )

    data class Coord(
        val lat: Double,
        val lon: Double
    )

    data class Main(
        val feels_like: Double,
        val grnd_level: Int,
        val humidity: Int,
        val pressure: Int,
        val sea_level: Int,
        val temp: Double,
        val temp_max: Double,
        val temp_min: Double
    ){
        fun getTempInCelcius(): String{
            return (temp - ("273.15").toFloat()).toString()
        }
    }

    data class Sys(
        val country: String,
        val sunrise: Int,
        val sunset: Int
    )




    data class Wind(
        val deg: Int,
        val speed: Double
    )

     fun updateLastFetchedTime(){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val formattedDate: String = dateFormat.format(System.currentTimeMillis())
        lastFetchedTime = formattedDate
    }
}