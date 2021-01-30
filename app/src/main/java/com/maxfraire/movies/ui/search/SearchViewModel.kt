package com.maxfraire.movies.ui.search

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.maxfraire.movies.data.MoviesRepository
import com.maxfraire.movies.ui.base.BaseMovieListViewModel
import com.maxfraire.movies.ui.models.MovieUI
import com.maxfraire.movies.ui.models.MoviesUIDataMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val mapper: MoviesUIDataMapper
) : BaseMovieListViewModel() {

    private val _searchQuery: MutableLiveData<String> = MutableLiveData()
    val searchQuery: LiveData<String> = _searchQuery

    val searchResult: LiveData<PagingData<MovieUI>> = searchQuery.switchMap { query ->
        repository.searchMovie(query)
            .map { pagingData -> pagingData.map { mapper.convert(it) } }
            .cachedIn(viewModelScope)
            .asLiveData()
    }

    fun search(query: String) {
        _searchQuery.postValue(query)
    }
}