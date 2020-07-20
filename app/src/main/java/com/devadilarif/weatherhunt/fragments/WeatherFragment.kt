package com.devadilarif.weatherhunt.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.HomeFragmentBinding
import com.devadilarif.weatherhunt.databinding.WeatherFragmentBinding
import com.devadilarif.weatherhunt.repo.local.model.Forcast
import com.devadilarif.weatherhunt.viewmodels.MyViewModelFactory
import com.devadilarif.weatherhunt.viewmodels.WeatherFragmentViewModel

class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            WeatherFragment()
    }

    private lateinit var viewModel: WeatherFragmentViewModel
    lateinit var binding : WeatherFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.weather_fragment,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.getParcelable<Forcast>("DATA_FORECAST").let {
            viewModel = ViewModelProviders.of(this, MyViewModelFactory(WeatherFragmentViewModel::class){
                WeatherFragmentViewModel(it)
            }).get(WeatherFragmentViewModel::class.java)
        }

        binding.vm = viewModel

    }

}
