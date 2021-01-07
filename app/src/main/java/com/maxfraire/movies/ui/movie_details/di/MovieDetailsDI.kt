package com.maxfraire.movies.ui.movie_details.di

import androidx.lifecycle.ViewModel
import com.maxfraire.movies.di.ViewModelKey
import com.maxfraire.movies.ui.movie_details.MovieDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Module(includes = [MovieDetailsViewModelModule::class])
class MovieDetailsFragmentModule { }

@Module
abstract class MovieDetailsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    @MovieDetailsScope
    abstract fun provideViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MovieDetailsScope

