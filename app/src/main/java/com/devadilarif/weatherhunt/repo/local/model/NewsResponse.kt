package com.devadilarif.weatherhunt.repo.local.model

data class NewsResponse (
    val status : String,
    val totalResults : Int,
    val articles : List<News>
)
