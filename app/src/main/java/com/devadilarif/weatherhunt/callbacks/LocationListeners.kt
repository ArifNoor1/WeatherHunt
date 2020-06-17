package com.devadilarif.weatherhunt.callbacks

import android.location.Location

interface LocationListeners {
    fun onLastLocationFound(location : Location)
}