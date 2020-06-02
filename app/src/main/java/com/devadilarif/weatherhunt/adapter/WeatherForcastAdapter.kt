package com.devadilarif.weatherhunt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.WeatherItemBinding

class WeatherForcastAdapter :
    RecyclerView.Adapter<WeatherForcastAdapter.WeatherForcastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForcastViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(LayoutInflater.from(parent.context),
            R.layout.weather_item, parent, false)
        return WeatherForcastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WeatherForcastViewHolder, position: Int) {
        TODO("Not yet implemented")
    }




    inner class WeatherForcastViewHolder(val weatherForcastDataBinding : WeatherItemBinding)
        : RecyclerView.ViewHolder(weatherForcastDataBinding.root) {

    }


}