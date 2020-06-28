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

    var nextTitle = ObservableField<String>("")
    private var currrentPosition = 0
    init {
        newsRepository.getNews { it ->
            news.set(it[0])
            it.forEach {
                newsList.add(it) }
            changeTitle()
        }





    }

    fun changeTitle(){
        if(!newsList.isEmpty() && currrentPosition < newsList.size - 1){
            nextTitle.set(newsList[currrentPosition + 1].title)
        }
    }


    fun changeNews(){

        currrentPosition++
        if(currrentPosition == newsList.size - 1) currrentPosition = 0
        news.set(newsList.get(currrentPosition))

        changeTitle()


    }
}
