package com.devadilarif.weatherhunt.repo

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.devadilarif.weatherhunt.repo.local.WeatherHuntDatabase
import com.devadilarif.weatherhunt.repo.local.model.COVID19
import com.devadilarif.weatherhunt.repo.remote.Networking
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.File

class CovidRepository(val owner : LifecycleOwner, val context : Context) {

    private var disposables : CompositeDisposable
    private var db : WeatherHuntDatabase
    init {
        disposables = CompositeDisposable()
        db = WeatherHuntDatabase.getInstance(context)

    }

    fun requestCovidUpdates(){
        disposables.add(
            Networking.create(Networking.COVID_19_BASE_URL, File(""), 5 * 1024 * 1024)
                .queryCovid19()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    db.covidDao().insertCovid19(it.data)
                    Timber.d("successful response of covid fetch request")
                },{
                    Timber.e("$it")
                }))
    }

    fun getCovidUpdates(onSuccess : (COVID19)->Unit){
        db.covidDao().getCovidUpdates().observe(owner, Observer {
            Timber.d("getCovidUpdates() $it")
            if(it != null){
                onSuccess(it)
            }
        })
    }

    fun onDestroy(){
        disposables.clear()
    }
}