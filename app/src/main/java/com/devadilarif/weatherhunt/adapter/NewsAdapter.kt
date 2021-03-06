package com.devadilarif.weatherhunt.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.R
import com.devadilarif.weatherhunt.databinding.NewsItemBinding
import com.devadilarif.weatherhunt.fragments.WebFragment
import com.devadilarif.weatherhunt.repo.local.model.News
import kotlinx.android.synthetic.main.news_item.view.*
import kotlinx.android.synthetic.main.weather_item.view.*
import kotlinx.android.synthetic.main.weather_item.view.card_view

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
            newsDataBinding.news = news
            newsDataBinding.root.card_view.setOnClickListener {
                showWebFragment(news)
            }

        }

        fun showWebFragment(news: News){
            val fragment = WebFragment()
            val activity = newsDataBinding.root.context as AppCompatActivity
            val data =  Bundle()
            data.putString("newsLink",news.url)
            fragment.arguments = data
            activity.supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,fragment).addToBackStack("backStack").commit()

        }
    }
}