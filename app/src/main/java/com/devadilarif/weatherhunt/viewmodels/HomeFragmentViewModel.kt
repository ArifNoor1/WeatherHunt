package com.devadilarif.weatherhunt.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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


    var weatherForecasts = MutableLiveData<List<Forcast>>()
    var topNews = MutableLiveData<List<News>>()
    var covid19Data = MutableLiveData<COVID19>()

    init {
        weatherRepository.getForecasts{
            weatherForecasts.postValue(it)

        }
        newsRepository.getTopHeadlines {
            topNews.postValue(it)
        }
        covidRepository.getCovidUpdates {
            covid19Data.postValue(it)
        }
    }
}
