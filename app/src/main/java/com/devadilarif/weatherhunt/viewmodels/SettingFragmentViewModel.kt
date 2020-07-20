package com.devadilarif.weatherhunt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.devadilarif.weatherhunt.adapter.NewsAdapter
import com.devadilarif.weatherhunt.repo.NewsRepository
import com.devadilarif.weatherhunt.repo.local.model.News

class SettingFragmentViewModel(val newsRepository : NewsRepository) : ViewModel() {
    // TODO: Implement the ViewModel



    fun setAdapter(recyclerView: RecyclerView){
        var newsAdapter = NewsAdapter(mutableListOf<News>())
        recyclerView.adapter = newsAdapter
        newsRepository.getAllBookmarkedNews {
            newsAdapter = NewsAdapter(it)
            recyclerView.adapter = newsAdapter
            newsAdapter.notifyDataSetChanged()
        }
    }
}
