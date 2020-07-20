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
import io.reactivex.Completable
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
        disposables.add(Networking.create(NEWS_BASE_URL, File(""), 5 * 1024 * 1024)
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

    fun requestNews(onResult : (Boolean) -> Unit){
        disposables.add(Networking.create(NEWS_BASE_URL, File(""), 5 * 1024 * 1024)
            .queryTopHeadlines("in","technology")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                it.articles.forEach {
                    db.newsDao().insertNews(it)
                    Timber.d("successful fetch of Top Headlines")
                }
                onResult(true)
            },{
                Timber.e("$it")
                onResult(false)
            }))
    }

    fun getTopHeadlines(onSuccess : (List<News>) -> Unit){
        val b  = db.newsDao().getAllNews().hasObservers()
        val a= db.newsDao().getAllNews().hasActiveObservers()


        db.newsDao().getTopNews().observe(owner, Observer{
            onSuccess(it)
        })
    }

    fun getNews(onSuccess: (List<News>) -> Unit){
        val b  = db.newsDao().getAllNews().hasObservers()
        val a= db.newsDao().getAllNews().hasActiveObservers()

//        var observer = Observer<List<News>>()
       db.newsDao().getAllNews().observe(owner, Observer{
            onSuccess(it)
        })


    }

    fun getAllBookmarkedNews(onResult: (List<News>) -> Unit){
        db.newsDao().getBookmarkedNews().observe(owner, Observer {
            onResult(it)
        })

    }

    fun bookmarkNews(news: News?){
        Completable.fromAction{
            news?.let {
                it.bookmark()
                db.newsDao().updateNews(it)
            }
        }.subscribeOn(Schedulers.io())
        .subscribe()


    }

    fun unbookmarkNews(news : News?){
        Completable.fromAction{
            news?.let {
                it.unbookmark()
                db.newsDao().updateNews(it)
            }
        }.subscribeOn(Schedulers.io())
            .subscribe()



    }

//    fun getAllNews():LiveData<News>{
//
//    }

    fun onDestroy(){
        disposables.clear()

    }
}