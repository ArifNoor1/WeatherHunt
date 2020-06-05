package com.devadilarif.weatherhunt.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.viewmodels.WeatherFragmentViewModel


class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            WeatherFragment()
    }

    private lateinit var viewModel: WeatherFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WeatherFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
