package com.devadilarif.weatherhunt.adapter

import android.app.Activity
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
        holder.cardView.setOnClickListener {
            holder.showWeatherFragment()
        }


    }

    inner class WeatherForcastViewHolder(val weatherForcastDataBinding : WeatherItemBinding)
        : RecyclerView.ViewHolder(weatherForcastDataBinding.root) {


        var cardView = weatherForcastDataBinding.root.card_view

        fun onBind(forcast : Forcast){
            weatherForcastDataBinding.weatherResponse = forcast

        }

        fun showWeatherFragment(){
//            weatherForcastDataBinding.root.
            var activity = weatherForcastDataBinding.root.context as AppCompatActivity
            val myFragment: Fragment = WeatherFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, myFragment).addToBackStack(null).commit()

        }


        /*fun dataToFragment(forecast: Forcast, context: Context) {

            val weatherFragment = WeatherFragment()
            val args = Bundle()
            args.putParcelable("DATA_FORECAST", forecast)
            weatherFragment.arguments = args



        }*/
    }


}