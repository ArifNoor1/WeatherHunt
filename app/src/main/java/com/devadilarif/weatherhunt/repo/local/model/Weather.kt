package com.devadilarif.weatherhunt.repo.local.model

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)