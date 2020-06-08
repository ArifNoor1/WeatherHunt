package com.devadilarif.weatherhunt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.NewsItemBinding
import com.devadilarif.weatherhunt.databinding.WeatherItemBinding

class NewsAdapter  : RecyclerView.Adapter<NewsAdapter.NewsViewHolder> (){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = DataBindingUtil.inflate<NewsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_item, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class NewsViewHolder(var newsDataBinding : NewsItemBinding)
        : RecyclerView.ViewHolder(newsDataBinding.root) {

    }
}