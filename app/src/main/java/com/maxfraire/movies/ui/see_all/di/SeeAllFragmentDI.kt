package com.maxfraire.movies.ui.see_all.di

import androidx.lifecycle.ViewModel
import com.maxfraire.movies.di.ViewModelKey
import com.maxfraire.movies.ui.see_all.SeeAllMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Module(includes = [SeeAllMoviesViewModelModule::class])
class SeeAllMoviesFragmentModule

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SeeAllMoviesFragmentScope

@Module
abstract class SeeAllMoviesViewModelModule {
    @Binds
    @IntoMap
    @SeeAllMoviesFragmentScope
    @ViewModelKey(SeeAllMoviesViewModel::class)
    abstract fun provideMoviesViewModel(viewModel: SeeAllMoviesViewModel): ViewModel
}
