package com.maxfraire.movies

import com.maxfraire.movies.di.DaggerAppComponent.builder
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MoviesApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        builder().application(this).build()

}
