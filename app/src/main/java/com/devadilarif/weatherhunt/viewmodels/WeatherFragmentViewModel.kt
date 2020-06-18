package com.devadilarif.weatherhunt.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devadilarif.weatherhunt.repo.WeatherRepository
import com.devadilarif.weatherhunt.repo.local.model.WeatherResponse

class WeatherFragmentViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    var currentWeather = MutableLiveData<WeatherResponse>()

    init {
        weatherRepository.getCurrentWeather {
            currentWeather.postValue(it)
        }
    }


}
