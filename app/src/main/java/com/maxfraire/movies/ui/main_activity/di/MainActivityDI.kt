package com.maxfraire.movies.ui.main_activity.di

import androidx.lifecycle.ViewModel
import com.maxfraire.movies.di.ViewModelKey
import com.maxfraire.movies.ui.favorites.FavoritesFragment
import com.maxfraire.movies.ui.favorites.di.FavoritesFragmentModule
import com.maxfraire.movies.ui.favorites.di.FavoritesScope
import com.maxfraire.movies.ui.main_activity.MainActivityViewModel
import com.maxfraire.movies.ui.movie_details.MovieDetailsFragment
import com.maxfraire.movies.ui.movie_details.di.MovieDetailsFragmentModule
import com.maxfraire.movies.ui.movie_details.di.MovieDetailsScope
import com.maxfraire.movies.ui.movies_list.MoviesFragment
import com.maxfraire.movies.ui.movies_list.di.MoviesFragmentModule
import com.maxfraire.movies.ui.movies_list.di.MoviesFragmentScope
import com.maxfraire.movies.ui.see_all.SeeAllMoviesFragment
import com.maxfraire.movies.ui.see_all.di.SeeAllMoviesFragmentModule
import com.maxfraire.movies.ui.see_all.di.SeeAllMoviesFragmentScope
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Suppress("unused")
@Module(includes = [
    MainActivityViewModelModule::class,
    MainActivityFragmentsBuildersModule::class
])
abstract class MainActivityModule

@Module
abstract class MainActivityViewModelModule {
    @Binds
    @IntoMap
    @MainActivityScope
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}

@Module
abstract class MainActivityFragmentsBuildersModule {

    @MoviesFragmentScope
    @ContributesAndroidInjector(modules = [MoviesFragmentModule::class])
    abstract fun contributeMoviesFragment(): MoviesFragment

    @SeeAllMoviesFragmentScope
    @ContributesAndroidInjector(modules = [SeeAllMoviesFragmentModule::class])
    abstract fun contributeSeeAllMoviesFragment(): SeeAllMoviesFragment

    @MovieDetailsScope
    @ContributesAndroidInjector(modules = [MovieDetailsFragmentModule::class])
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment

    @FavoritesScope
    @ContributesAndroidInjector(modules = [FavoritesFragmentModule::class])
    abstract fun contributeFavoritesFragment(): FavoritesFragment
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainActivityScope
