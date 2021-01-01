package com.maxfraire.movies

import com.jakewharton.threetenabp.AndroidThreeTen
import com.maxfraire.movies.di.DaggerAppComponent.builder
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MoviesApplication: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        AndroidThreeTen.init(this)
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        builder().application(this).build()

}
