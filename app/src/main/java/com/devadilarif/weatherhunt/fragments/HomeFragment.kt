package com.devadilarif.weatherhunt.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.HomeFragmentBinding
import com.devadilarif.weatherhunt.repo.CovidRepository
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.WeatherRepository
import com.devadilarif.weatherhunt.viewmodels.HomeFragmentViewModel
import com.devadilarif.weatherhunt.viewmodels.MyViewModelFactory
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

//    companion object {
//        fun newInstance() = HomeFragment()
//    }

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

    fun handleViews(){
        refreshLayoutHomeFragment.setOnRefreshListener {
            viewModel.refreshData()
        }
    }

    override fun onAttach(context: Context) {
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
        viewModel = ViewModelProviders.of(this,MyViewModelFactory(HomeFragmentViewModel::class){
            HomeFragmentViewModel(weatherRepository, newsRepository, covidRepository)
        }).get(HomeFragmentViewModel::class.java)
        binding.vm = viewModel

        rv_newsCards.layoutManager = LinearLayoutManager(context)
        viewModel.setNewsAdapter(rv_newsCards)


        val layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        rv_weatherCards.layoutManager = layoutManager
        viewModel.setWeatherForecastAdapter(rv_weatherCards)
        viewModel.onApiRefreshSuccess.observe(viewLifecycleOwner, Observer {
           if(refreshLayoutHomeFragment.isRefreshing) refreshLayoutHomeFragment.isRefreshing = false

        })

        viewModel.onDataLoaded.observe(viewLifecycleOwner, Observer {
            if(it){
                shimmer_view_container.stopShimmer()
                shimmer_view_container.hideShimmer()
            }

        })
        handleViews()

    }

    override fun onStop() {
        super.onStop()

//        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

    }



}
