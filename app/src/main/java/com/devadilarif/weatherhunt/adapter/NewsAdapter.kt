package com.devadilarif.weatherhunt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.NewsItemBinding
import com.devadilarif.weatherhunt.repo.local.model.News

class NewsAdapter(val topHeadlines : List<News>)  : RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder> (){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = DataBindingUtil.inflate<NewsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_item, parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return topHeadlines.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        holder.onBind(topHeadlines[position])


    }

    inner class NewsItemViewHolder(var newsDataBinding : NewsItemBinding)
        : RecyclerView.ViewHolder(newsDataBinding.root) {

        fun onBind(news : News){

        }
    }
}