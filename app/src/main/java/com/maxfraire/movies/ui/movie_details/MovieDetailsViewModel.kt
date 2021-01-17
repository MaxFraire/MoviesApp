package com.maxfraire.movies.ui.movie_details

import androidx.lifecycle.*
import com.maxfraire.movies.data.MoviesRepository
import com.maxfraire.movies.data.common.doIfError
import com.maxfraire.movies.data.common.doIfLoading
import com.maxfraire.movies.data.common.doIfSuccess
import com.maxfraire.movies.ui.models.MovieDetailsUI
import com.maxfraire.movies.ui.models.MoviesUIDataMapper
import com.maxfraire.movies.util.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val mapper: MoviesUIDataMapper
) : ViewModel() {

    private val _onBackPressed = MutableLiveData<Event<Any>>()
    val onBackPressed = _onBackPressed

    private val _movie = MutableLiveData<MovieDetailsUI>()
    val movie: LiveData<MovieDetailsUI> = _movie

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> = _loading

    val isFavorite: LiveData<Boolean> = _movie.map { it.isFavorite }

    fun setMovieId(movieId: Int) {
        viewModelScope.launch {
            moviesRepository.getMovieById(movieId).collect {
                it.doIfSuccess { movie ->
                    _loading.postValue(false)
                    movie?.let {
                        _movie.postValue(mapper.convertToMovieDetail(movie))
                    }
                }
                .doIfError { _, _ ->
                    _loading.postValue(false)
                }
                .doIfLoading {
                    _loading.postValue(true)
                }
            }

        }
    }

    fun toggleFavorites() {
        viewModelScope.launch {
            movie.value?.let {
                moviesRepository.setFavorite(it.id, it.isFavorite.not())
            }
        }
    }

    fun navigateBack() {
        onBackPressed.value = Event(Any())
    }

}