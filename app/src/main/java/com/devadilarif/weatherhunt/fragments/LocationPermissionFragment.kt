package com.devadilarif.weatherhunt.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.devadilarif.weatherhunt.R

class LocationPermissionFragment : Fragment() {


    companion object{
        const val LOCATION_PERMISSION_RC = 1
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_permission, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        askLocationPermission()
    }

    fun askLocationPermission(){
        if(checkSelfPermission(context!!,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),LOCATION_PERMISSION_RC)
        }else{
            //move to another fragment
            moveToStorageFragment()
        }
    }

    fun moveToStorageFragment(){

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == LOCATION_PERMISSION_RC && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            moveToStorageFragment()
        }else{
            Toast.makeText(context,resources.getString(R.string.location_permission_not_given),Toast.LENGTH_LONG).show()
        }
    }


}