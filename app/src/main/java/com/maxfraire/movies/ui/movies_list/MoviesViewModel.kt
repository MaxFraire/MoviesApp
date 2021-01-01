package com.maxfraire.movies.ui.movies_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxfraire.movies.data.MoviesRepository
import com.maxfraire.movies.data.common.doIfLoading
import com.maxfraire.movies.data.common.doIfSuccess
import com.maxfraire.movies.ui.models.MovieListTypeUI
import com.maxfraire.movies.ui.models.MovieUI
import com.maxfraire.movies.ui.models.MoviesUIDataMapper
import com.maxfraire.movies.util.Event
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val mapper: MoviesUIDataMapper
) : ViewModel() {

    private val _popularMovies: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val popularMovies: LiveData<List<MovieUI>> = _popularMovies

    private val _upcomingMovies: MutableLiveData<List<MovieUI>> = MutableLiveData()
    val upcomingMovies: LiveData<List<MovieUI>> = _upcomingMovies

    private val _navigateToSeeAll = MutableLiveData<Event<MovieListTypeUI>>()
    val navigateToSeeAll = _navigateToSeeAll

    init {
        viewModelScope.launch {
            moviesRepository.getMovies(mapper.convert(MovieListTypeUI.Popular), FIRST_PAGE)
                .collect { response ->
                    response.doIfSuccess {
                        _popularMovies.postValue(it?.results?.map { movie ->
                            mapper.convert(movie)
                        })
                    }
                }

            moviesRepository.getMovies(mapper.convert(MovieListTypeUI.Upcoming), FIRST_PAGE)
                .collect { response ->
                    response.doIfSuccess {
                        _upcomingMovies.postValue(it?.results?.map { movie ->
                            mapper.convert(movie)
                        })
                    }
                }
        }
    }

    fun navigateToSeeAllFragment(movieListType: MovieListTypeUI) {
        _navigateToSeeAll.value = Event(movieListType)
    }

    companion object {
        private const val FIRST_PAGE = 1
    }
}