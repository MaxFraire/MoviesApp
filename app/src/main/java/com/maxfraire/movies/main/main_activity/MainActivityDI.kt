package com.maxfraire.movies.main.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxfraire.movies.core.MoviesViewModelFactory
import com.maxfraire.movies.core.di.ViewModelKey
import com.maxfraire.movies.main.main_activity.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module(includes = [
    MainActivityViewModelModule::class,
    ViewModelFactoryModule::class
])
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}

@Module
abstract class MainActivityViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: MoviesViewModelFactory): ViewModelProvider.Factory
}
