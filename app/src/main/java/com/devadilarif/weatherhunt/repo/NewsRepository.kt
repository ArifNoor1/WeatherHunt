package com.devadilarif.weatherhunt.repo

import android.content.Context
import android.net.Network
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.devadilarif.weatherhunt.repo.local.WeatherHuntDatabase
import com.devadilarif.weatherhunt.repo.local.model.News
import com.devadilarif.weatherhunt.repo.remote.Networking
import com.devadilarif.weatherhunt.repo.remote.Networking.NEWS_BASE_URL
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.File

class NewsRepository(val owner : LifecycleOwner, val context : Context) {
    private var db : WeatherHuntDatabase
    private var disposables : CompositeDisposable
    init {
        db = WeatherHuntDatabase.getInstance(context)
        disposables = CompositeDisposable()
    }

    fun requestTopHeadlines(){
        disposables.add(Networking.create(NEWS_BASE_URL, File(""), 1024)
            .queryTopHeadlines("in","technology")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                it.articles.forEach {
                    db.newsDao().insertNews(it)
                    Timber.d("successful fetch of Top Headlines")
                }
            },{
                Timber.e("$it")
            }))
    }

    fun requestNews(){
        disposables.add(Networking.create(NEWS_BASE_URL, File(""), 1024)
            .queryTopHeadlines("in","technology")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                it.articles.forEach {
                    db.newsDao().insertNews(it)
                    Timber.d("successful fetch of Top Headlines")
                }
            },{
                Timber.e("$it")
            }))
    }

    fun getTopHeadlines(onSuccess : (List<News>) -> Unit){
        db.newsDao().getTopNews().observe(owner, Observer{
            onSuccess(it)
        })
    }

//    fun getAllNews():LiveData<News>{
//
//    }
}