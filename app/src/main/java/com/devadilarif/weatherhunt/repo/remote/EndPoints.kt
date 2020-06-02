package com.devadilarif.weatherhunt.repo.remote

class EndPoints {

    var weatherBaseUrl = "https://api.openweathermap.org/data/2.5/"
    val newsBaseUrl = "http://newsapi.org/v2/top-headlines"
    var dailyForcastEndpoint = "https://api.openweathermap.org/data/2.5/onecall?lat=26.8467&lon=80.9462&exclude=hourly,current&appid=15bb59511e15f1086dbac85cf0989b9a"
    var currentWeatherEndPoint = "http://api.openweathermap.org/data/2.5/weather?q=lucknow&appid=15bb59511e15f1086dbac85cf0989b9a"

    var newsEndPoint = "\n" +
            "http://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=e45eba9d14604d7dabbfbe7f3db2db90"

    val WEATHER_API_KEY = "15bb59511e15f1086dbac85cf0989b9a"
    var NEWS_API_KEY = "e45eba9d14604d7dabbfbe7f3db2db90"


}