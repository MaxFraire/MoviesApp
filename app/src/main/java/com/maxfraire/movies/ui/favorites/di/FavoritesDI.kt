package com.maxfraire.movies.ui.favorites.di

import androidx.lifecycle.ViewModel
import com.maxfraire.movies.di.ViewModelKey
import com.maxfraire.movies.ui.favorites.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Module(includes = [FavoritesViewModelModule::class])
abstract class FavoritesFragmentModule

@Module
abstract class FavoritesViewModelModule() {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    @FavoritesScope
    abstract fun provideViewModel(viewModel: FavoritesViewModel): ViewModel

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FavoritesScope