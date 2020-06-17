package com.devadilarif.weatherhunt

import android.content.Context
import android.location.Location
import com.devadilarif.weatherhunt.callbacks.LocationListeners
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import java.lang.ref.WeakReference

/*
    1. Fetch the current location here
    2. Pass the current location to listeners
 */
class LocationManager(private val context : Context) {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var listeners = mutableListOf<WeakReference<LocationListeners>>()
    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener {
            listeners.forEach { listener->
                listener.get()?.onLastLocationFound(it)
            }
        }
    }

    fun addListener(listener : LocationListeners){
        listeners.add(WeakReference(listener))
    }

}