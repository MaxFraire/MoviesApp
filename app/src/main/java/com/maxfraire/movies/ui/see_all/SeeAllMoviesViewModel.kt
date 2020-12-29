package com.maxfraire.movies.ui.see_all

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.maxfraire.movies.data.MoviesRepository
import com.maxfraire.movies.ui.models.MovieListTypeUI
import com.maxfraire.movies.ui.models.MovieUI
import com.maxfraire.movies.ui.models.MoviesUIDataMapper
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class SeeAllMoviesViewModel @Inject constructor(
    private val mapper: MoviesUIDataMapper,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _listType = MutableLiveData<MovieListTypeUI>()
    val listType: LiveData<MovieListTypeUI> = _listType

    private val _movies: MediatorLiveData<PagingData<MovieUI>> = MediatorLiveData()
    val movies: LiveData<PagingData<MovieUI>> = _movies

    init {
        _movies.addSource(_listType) { listType ->
            moviesRepository.getPaginatedMovies(mapper.convert(listType))
                .map { pagingData -> pagingData.map { mapper.convert(it) } }
                .cachedIn(viewModelScope)
                .onEach { _movies.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun setListType(listType: MovieListTypeUI) {
        _listType.postValue(listType)
    }

}