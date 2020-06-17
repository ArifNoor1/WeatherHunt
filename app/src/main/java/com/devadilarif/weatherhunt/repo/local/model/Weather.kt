package com.devadilarif.weatherhunt.repo.local.model

data class Weather(
    var description: String,
    var icon: String,
    var id: Int,
    var main: String
)