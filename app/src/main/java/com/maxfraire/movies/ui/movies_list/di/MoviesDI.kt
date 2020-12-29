package com.maxfraire.movies.ui.movies_list.di

import androidx.lifecycle.ViewModel
import com.maxfraire.movies.di.ViewModelKey
import com.maxfraire.movies.ui.movies_list.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@Module(includes = [MoviesViewModelModule::class])
class MoviesFragmentModule

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MoviesFragmentScope

@Module
abstract class MoviesViewModelModule {
    @Binds
    @IntoMap
    @MoviesFragmentScope
    @ViewModelKey(MoviesViewModel::class)
    abstract fun provideMoviesViewModel(viewModel: MoviesViewModel): ViewModel
}
