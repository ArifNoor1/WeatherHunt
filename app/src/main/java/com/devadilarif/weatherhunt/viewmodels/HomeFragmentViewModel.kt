package com.devadilarif.weatherhunt.viewmodels

import android.content.Context
import android.location.Location
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.adapter.NewsAdapter
import com.devadilarif.weatherhunt.adapter.WeatherForecastAdapter
import com.devadilarif.weatherhunt.repo.CovidRepository
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.WeatherRepository
import com.devadilarif.weatherhunt.repo.local.model.COVID19
import com.devadilarif.weatherhunt.repo.local.model.Forcast
import com.devadilarif.weatherhunt.repo.local.model.News
import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse

class HomeFragmentViewModel(
    private val weatherRepository: WeatherRepository,
    private val newsRepository: NewsRepository,
    private val covidRepository: CovidRepository
) : ViewModel() {

    var covid19Data = ObservableField<COVID19>()

    var onNewsApiSuccess = MutableLiveData<Boolean>()
    //TODO: Default and secondry constuctor discussion
    init {
        val location = Location("")
        location.latitude =26.8467
        location.longitude = 80.9462
        covidRepository.requestCovidUpdates()
        weatherRepository.requestForecasts(location)
        newsRepository.requestNews{

        }
//        weatherRepository.re
        covidRepository.getCovidUpdates {
            covid19Data.set(it)
        }
    }

    fun setWeatherForecastAdapter(recyclerView: RecyclerView){
        var forecastAdapter = WeatherForecastAdapter(mutableListOf<Forcast>())
        recyclerView.adapter = forecastAdapter
        weatherRepository.getForecasts{
            forecastAdapter = WeatherForecastAdapter(it)
            recyclerView.adapter = forecastAdapter

            forecastAdapter.notifyDataSetChanged()
        }
    }

    //TODO: Discuss the background threading

    fun setNewsAdapter(recyclerView: RecyclerView){
        var newsAdapter = NewsAdapter(mutableListOf<News>())
        recyclerView.adapter = newsAdapter
        newsRepository.getTopHeadlines {
            newsAdapter = NewsAdapter(it)
            recyclerView.adapter = newsAdapter
            newsAdapter.notifyDataSetChanged()

            onNewsApiSuccess.postValue(true)
        }
    }

    fun refreshData(){
        covidRepository.requestCovidUpdates()
        val location = Location("")
        location.latitude =26.8467
        location.longitude = 80.9462
        weatherRepository.requestForecasts(location)
        newsRepository.requestNews{
            onNewsApiSuccess.postValue(it)
        }
    }


}
