package com.devadilarif.weatherhunt.repo.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlin.math.roundToInt

@Entity
data class Forcast(
    var clouds: Int,
    var dew_point: Double,

    @PrimaryKey
    var dt: Int,

//    @Ignore
//    var feels_like: FeelsLike,
    var humidity: Int,
    var pressure: Int,
    var sunrise: Int,
    var sunset: Int,

    @Embedded
    var temp: Temp,

    var uvi: Double,


    var weather: List<Weather>,
    var wind_deg: Int,
    var wind_speed: Double
){
    data class FeelsLike(
        var day: Double,
        var eve: Double,
        var morn: Double,
        var night: Double
    )

    data class Temp(
        var day: Double,
        var eve: Double,
        var max: Double,
        var min: Double,
        var morn: Double,
        var night: Double
    ){
        fun getTempInCelcius(): String{
            return (max - ("273.15").toFloat()).roundToInt().toString()
        }
    }


    
}