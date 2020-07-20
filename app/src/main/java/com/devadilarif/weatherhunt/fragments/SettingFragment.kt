package com.devadilarif.weatherhunt.fragments

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.WorkManager
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.StartupActivity.Companion.NOTIFICATION_WORK_TAG
import com.devadilarif.weatherhunt.databinding.SettingFragmentBinding
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.viewmodels.MyViewModelFactory
import com.devadilarif.weatherhunt.viewmodels.NewsFragmentViewModel
import com.devadilarif.weatherhunt.viewmodels.SettingFragmentViewModel
import com.google.android.libraries.places.api.model.Place
import com.rtchagas.pingplacepicker.PingPlacePicker
import kotlinx.android.synthetic.main.setting_fragment.*


class SettingFragment : Fragment() {

    companion object {
        fun newInstance() =
            SettingFragment()
    }

    private lateinit var viewModel: SettingFragmentViewModel
    private lateinit var binding : SettingFragmentBinding
    private lateinit var newsRepository : NewsRepository
    private val pingActivityRequestCode = 1001

    private val SETTING_SHARED_PREF = "SETTING_SHARED_PREF"
    private val SELECTED_ADDRESS = "SELECTED_ADDRESS"
    private val NOTIFICATION = "NOTIFICATION"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.setting_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsRepository = NewsRepository(viewLifecycleOwner,context!!)
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(SettingFragmentViewModel::class){
            SettingFragmentViewModel(newsRepository)
        }).get(SettingFragmentViewModel::class.java)


            binding.vm = viewModel
        // TODO: Use the ViewModel

        binding.recyclerViewBookmark.layoutManager = LinearLayoutManager(context)
        viewModel.setAdapter(binding.recyclerViewBookmark)

        populateView()

        tv_change.setOnClickListener {
            showPlacePicker()
        }

        switchButton.isChecked = getNotificationStatus()
        switchButton.setOnCheckedChangeListener { _, isChecked ->
            setNotification(isChecked)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == pingActivityRequestCode) && (resultCode == Activity.RESULT_OK)) {
            val place: Place? = PingPlacePicker.getPlace(data!!)

            tv_location_icon.text = place?.address
            storeSelectedAddress(place?.address)
            Toast.makeText(context, "You selected: ${place?.name}\n ${place?.id}", Toast.LENGTH_LONG).show()
        }
    }

    fun showPlacePicker(){
        val builder = PingPlacePicker.IntentBuilder()

        builder.setAndroidApiKey(getString(R.string.key_google_apis_android))
            .setMapsApiKey(getString(R.string.key_google_apis_maps))

        // If you want to set a initial location
        // rather then the current device location.
        // pingBuilder.setLatLng(LatLng(37.4219999, -122.0862462))

        try {
            val placeIntent = builder.build(activity as Activity)
            startActivityForResult(placeIntent, pingActivityRequestCode)
        } catch (ex: Exception) {
            Toast.makeText(context, "Google Play Services is not Available ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun populateView(){
        tv_location_icon.text = getAddress() ?: "Unknown Address"
    }



    fun storeSelectedAddress(address : String?){
        val sharedPreference = activity?.getSharedPreferences(SETTING_SHARED_PREF,MODE_PRIVATE)
        sharedPreference?.edit()?.putString(SELECTED_ADDRESS,address)?.apply()
    }

    fun getAddress(): String?{
        val sharedPreference = activity?.getSharedPreferences(SETTING_SHARED_PREF,MODE_PRIVATE)
        return sharedPreference?.getString(SELECTED_ADDRESS,null)
    }

    fun setNotification(status : Boolean){
        val sharedPreference = activity?.getSharedPreferences(SETTING_SHARED_PREF,MODE_PRIVATE)
        sharedPreference?.edit()?.putBoolean(NOTIFICATION,status)?.apply()

        if(!status) disableWorkManager()
    }

    fun disableWorkManager(){
        WorkManager.getInstance(context!!).cancelAllWorkByTag(NOTIFICATION_WORK_TAG)
    }

    fun getNotificationStatus(): Boolean{
        val sharedPreference = activity?.getSharedPreferences(SETTING_SHARED_PREF,MODE_PRIVATE)
        return sharedPreference?.getBoolean(NOTIFICATION,false) ?: false
    }


}
