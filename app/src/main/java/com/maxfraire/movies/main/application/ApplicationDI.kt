package com.maxfraire.movies.main.application

import android.app.Application
import com.maxfraire.movies.main.main_activity.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AndroidInjectionModule::class
])
interface AppComponent: AndroidInjector<MoviesApplication>{
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module(includes = [MainActivityModule::class])
class AppModule