package com.maxfraire.movies.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxfraire.movies.util.Event

open class BaseMovieListViewModel() : ViewModel() {

    private val _navigateToMovieDetails = MutableLiveData<Event<Int>>()
    val navigateToMovieDetails = _navigateToMovieDetails

    private val _navigateBack = MutableLiveData<Event<Any>>()
    val navigateBack: LiveData<Event<Any>> = _navigateBack

    fun navigateToMovieDetails(movieId: Int) {
        _navigateToMovieDetails.value = Event(movieId)
    }

    fun navigateBack() {
        _navigateBack.value = Event(Any())
    }
}