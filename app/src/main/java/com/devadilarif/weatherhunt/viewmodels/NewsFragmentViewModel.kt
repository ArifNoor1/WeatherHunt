package com.devadilarif.weatherhunt.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.local.model.News

class NewsFragmentViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private var news = MutableLiveData<List<News>>()

    init {
        newsRepository.getTopHeadlines {
            news.postValue(it)
        }
    }
}
