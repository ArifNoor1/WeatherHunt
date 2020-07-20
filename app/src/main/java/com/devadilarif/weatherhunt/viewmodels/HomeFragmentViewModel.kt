package com.devadilarif.weatherhunt.viewmodels

import android.os.Handler
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.devadilarif.weatherhunt.SwipeController
import com.devadilarif.weatherhunt.adapter.NewsAdapter
import com.devadilarif.weatherhunt.adapter.WeatherForecastAdapter
import com.devadilarif.weatherhunt.repo.CovidRepository
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.WeatherRepository
import com.devadilarif.weatherhunt.repo.local.model.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class HomeFragmentViewModel(
    private val weatherRepository: WeatherRepository,
    private val newsRepository: NewsRepository,
    private val covidRepository: CovidRepository
) : ViewModel() {

    var covid19Data = ObservableField<COVID19>()
    var onApiRefreshSuccess = MutableLiveData<Boolean>()

    var onDataLoaded = MutableLiveData<Boolean>()


    //TODO: Default and secondry constuctor discussion
    init {
        requestCovid()
        requestNews()
        requestWeather()
        covidRepository.getCovidUpdates {
            covid19Data.set(it)
        }
    }

    fun requestCovid(){
        covidRepository.requestCovidUpdates()
    }

    fun requestNews(){
        newsRepository.requestNews{

        }
    }

    fun requestWeather(){
//        val location = Location("")
//        location.latitude =26.8467
//        location.longitude = 80.9462
        weatherRepository.requestForecasts()

    }

    fun setWeatherForecastAdapter(recyclerView: RecyclerView){
        var forecastAdapter = WeatherForecastAdapter(fakeForcastItems())
        recyclerView.adapter = forecastAdapter
        weatherRepository.getForecasts{
            forecastAdapter = WeatherForecastAdapter(it)
            recyclerView.adapter = forecastAdapter
            forecastAdapter.notifyDataSetChanged()
        }
    }


    private fun fakeNewsItems():MutableList<News>{
        val list = mutableListOf<News>().apply {
            add(News("","","","", Source("",""),"","",null,false))
            add(News("","","","", Source("",""),"","",null,false))
            add(News("","","","", Source("",""),"","",null,false))
        }
        return list
    }

    private fun fakeForcastItems():MutableList<Forcast>{
        val weather = mutableListOf<Weather>().apply {
            add(Weather("","",1,""))
        }
        val list =  mutableListOf<Forcast>().apply {
            add(Forcast(0,0.0,0,0,0,0,0,Forcast.Temp(1.0,1.0,0.0,1.0,1.0,1.0),1.0,weather ,0,1.0))
            add(Forcast(0,0.0,0,0,0,0,0,Forcast.Temp(1.0,1.0,0.0,1.0,1.0,1.0),1.0, weather,0,1.0))
            add(Forcast(0,0.0,0,0,0,0,0,Forcast.Temp(1.0,1.0,0.0,1.0,1.0,1.0),1.0, weather,0,1.0))
            add(Forcast(0,0.0,0,0,0,0,0,Forcast.Temp(1.0,1.0,0.0,1.0,1.0,1.0),1.0, weather,0,1.0))
            add(Forcast(0,0.0,0,0,0,0,0,Forcast.Temp(1.0,1.0,0.0,1.0,1.0,1.0),1.0, weather,0,1.0))
            add(Forcast(0,0.0,0,0,0,0,0,Forcast.Temp(1.0,1.0,0.0,1.0,1.0,1.0),1.0, weather,0,1.0))
            add(Forcast(0,0.0,0,0,0,0,0,Forcast.Temp(1.0,1.0,0.0,1.0,1.0,1.0),1.0, weather,0,1.0))

        }
        return list
    }

    //TODO: Discuss the background threading

    fun setNewsAdapter(recyclerView: RecyclerView){
        var newsAdapter = NewsAdapter(fakeNewsItems())

        recyclerView.adapter = newsAdapter

        newsRepository.getTopHeadlines {

            newsAdapter = NewsAdapter(it)
            recyclerView.adapter = newsAdapter
            newsAdapter.notifyDataSetChanged()
            onDataLoaded.postValue(true)
            //TODO: It should update different observable which can show error message
            onApiRefreshSuccess.postValue(true)
        }
    }

    fun refreshData(){
        covidRepository.requestCovidUpdates()
//        val location = Location("")
//        location.latitude =26.8467
//        location.longitude = 80.9462
        weatherRepository.requestForecasts()
        newsRepository.requestNews{
            onApiRefreshSuccess.postValue(it)
        }
    }

    fun getCurrentDate() : String{
        var calendar = Calendar.getInstance()
        val dayName = getDay(calendar.get(Calendar.DAY_OF_WEEK))


        val dateFormat = SimpleDateFormat("dd-MM")
        val formattedDate: String = dateFormat.format(System.currentTimeMillis())
        return  "$dayName $formattedDate"

    }

    fun getDay(day : Int): String? {
     val days = mutableMapOf<Int,String>().apply {
           put(1,"Sun")
           put(2,"Mon")
           put(3,"Tues")
           put(4,"Wed")
           put(5,"Thr")
           put(6,"Fri")
           put(7,"Sat")
        }

        return days[day]

    }
//
//    fun onStop(){
//        cle
//        newsRepository.onDestroy()
//    }


}
