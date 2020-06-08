package com.devadilarif.weatherhunt.repo.local.model

data class ForecastResponse(
    var lat : Double,
    var lon : Double,
    var timezone : String,
    var timezone_offset : Long,
    var daily : List<DailyForcast>
)