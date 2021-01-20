package com.maxfraire.movies.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.maxfraire.movies.data.MoviesRepository
import com.maxfraire.movies.ui.base.BaseMovieListViewModel
import com.maxfraire.movies.ui.models.MovieUI
import com.maxfraire.movies.ui.models.MoviesUIDataMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val mapper: MoviesUIDataMapper
) : BaseMovieListViewModel() {

    private val _displayGridLayout = MutableLiveData<Boolean>(true)
    val displayGridLayout: LiveData<Boolean> = _displayGridLayout

    val favorites: LiveData<List<MovieUI>> = repository.getFavorites()
        .map { mapper.convert(it) }
        .asLiveData()

    fun toggleLayoutManager() {
        _displayGridLayout.postValue((displayGridLayout.value?.not()))
    }
}