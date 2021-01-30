package com.maxfraire.movies.ui.search.di

import androidx.lifecycle.ViewModel
import com.maxfraire.movies.di.ViewModelKey
import com.maxfraire.movies.ui.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@Module(includes = [SearchViewModelModule::class])
abstract class SearchFragmentModule

@Module
abstract class SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @SearchFragmentScope
    abstract fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class SearchFragmentScope