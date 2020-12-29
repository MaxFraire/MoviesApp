package com.maxfraire.movies.di

import android.app.Application
import com.maxfraire.movies.MoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Scope

@ApplicationScope
@Component(modules = [
    AppModule::class,
    ApiModule::class,
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

@Module(includes = [ActivityBuildersModule::class])
class AppModule

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
