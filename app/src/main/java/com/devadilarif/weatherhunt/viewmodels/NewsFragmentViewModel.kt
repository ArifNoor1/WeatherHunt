package com.devadilarif.weatherhunt.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.local.model.News

class NewsFragmentViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var news = ObservableField<News>()

    private var newsList = arrayListOf<News>()
    private var currrentPosition = 0
    init {
        newsRepository.getTopHeadlines { it ->
            news.set(it.get(0))
            it.forEach { newsList.add(it) }
        }
    }

    fun getNextNewsTitle() : String{
        return if(currrentPosition != newsList.size - 1) newsList.get(currrentPosition + 1).title else newsList.get(currrentPosition).title
    }

    fun changeNews(){
        currrentPosition++
        if(currrentPosition == newsList.size - 1) currrentPosition = 0

        news.set(newsList.get(currrentPosition))
    }
}
