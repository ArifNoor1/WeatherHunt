package com.devadilarif.weatherhunt.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.HomeFragmentBinding
import com.devadilarif.weatherhunt.repo.CovidRepository
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.WeatherRepository
import com.devadilarif.weatherhunt.viewmodels.HomeFragmentViewModel
import com.devadilarif.weatherhunt.viewmodels.MyViewModelFactory


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeFragmentViewModel
    lateinit var binding : HomeFragmentBinding
    private lateinit var weatherRepository : WeatherRepository
    private lateinit var newsRepository : NewsRepository
    private lateinit var covidRepository: CovidRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.home_fragment,container,false)
        weatherRepository = WeatherRepository(context!!,viewLifecycleOwner)
        newsRepository = NewsRepository(viewLifecycleOwner, context!!)
        covidRepository = CovidRepository(viewLifecycleOwner, context!!)
        return binding.root
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!,MyViewModelFactory(HomeFragmentViewModel::class){
            HomeFragmentViewModel(weatherRepository, newsRepository, covidRepository)
        }).get(HomeFragmentViewModel::class.java)
        // TODO: Use the ViewModel
        binding.vm = viewModel

    }



}
