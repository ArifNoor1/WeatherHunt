package com.devadilarif.weatherhunt.adapter

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.WeatherItemBinding
import com.devadilarif.weatherhunt.fragments.WeatherFragment
import com.devadilarif.weatherhunt.repo.local.model.Forcast
import kotlinx.android.synthetic.main.weather_item.view.*


class WeatherForecastAdapter(val forecasts: List<Forcast>) :
    RecyclerView.Adapter<WeatherForecastAdapter.WeatherForcastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForcastViewHolder {
        val binding = DataBindingUtil.inflate<WeatherItemBinding>(LayoutInflater.from(parent.context),
            R.layout.weather_item, parent, false)
        return WeatherForcastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(holder: WeatherForcastViewHolder, position: Int) {
        holder.onBind(forecasts[position])


    }

    inner class WeatherForcastViewHolder(val weatherForcastDataBinding : WeatherItemBinding)
        : RecyclerView.ViewHolder(weatherForcastDataBinding.root) {



        fun onBind(forcast : Forcast){
            weatherForcastDataBinding.weatherResponse = forcast
            weatherForcastDataBinding.root.card_view.setOnClickListener {
                showWeatherFragment(forcast)
            }

        }

        fun showWeatherFragment(forecast : Forcast){
            val activity = weatherForcastDataBinding.root.context as AppCompatActivity
            val weatherFragment = WeatherFragment()
            //add Object Data to fragment
            val args = Bundle()
            args.putParcelable("DATA_FORECAST", forecast)
            weatherFragment.arguments = args

            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, weatherFragment).addToBackStack("backstack").commit()

        }


       /* fun dataToFragment(forecast: Forcast) {
            val weatherFragment = WeatherFragment()
            val args = Bundle()
            args.putParcelable("DATA_FORECAST", forecast)
            weatherFragment.arguments = args

        }*/
    }


}