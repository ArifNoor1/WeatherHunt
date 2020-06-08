package com.devadilarif.weatherhunt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.WeatherItemBinding
import com.devadilarif.weatherhunt.repo.local.model.DailyForcast
import com.devadilarif.weatherhunt.repo.local.model.ForecastResponse

class WeatherForecastAdapter(val forecastResponse: ForecastResponse) :
    RecyclerView.Adapter<WeatherForecastAdapter.WeatherForcastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForcastViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(LayoutInflater.from(parent.context),
            R.layout.weather_item, parent, false)
        return WeatherForcastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return forecastResponse.daily.size
    }

    override fun onBindViewHolder(holder: WeatherForcastViewHolder, position: Int) {
        holder.onBind(forecastResponse.daily[position])
    }

    inner class WeatherForcastViewHolder(val weatherForcastDataBinding : WeatherItemBinding)
        : RecyclerView.ViewHolder(weatherForcastDataBinding.root) {

        fun onBind(dailyForcast : DailyForcast){
            weatherForcastDataBinding.weatherResponse = dailyForcast
        }
    }


}