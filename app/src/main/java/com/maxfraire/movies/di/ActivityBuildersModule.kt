package com.maxfraire.movies.di

import androidx.lifecycle.ViewModelProvider
import com.maxfraire.movies.util.MoviesViewModelFactory
import com.maxfraire.movies.ui.main_activity.MainActivity
import com.maxfraire.movies.ui.main_activity.di.MainActivityModule
import com.maxfraire.movies.ui.main_activity.di.MainActivityScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelFactoryModule::class])
abstract class ActivityBuildersModule{
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @MainActivityScope
    abstract fun contributeMainActivity(): MainActivity
}

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: MoviesViewModelFactory): ViewModelProvider.Factory
}
