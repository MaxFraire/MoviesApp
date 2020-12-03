package com.maxfraire.movies.main.application

import com.maxfraire.movies.main.application.DaggerAppComponent.builder
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MoviesApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        builder().application(this).build()

}
