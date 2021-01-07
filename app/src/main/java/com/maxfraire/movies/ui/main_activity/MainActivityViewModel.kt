package com.maxfraire.movies.ui.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxfraire.movies.util.Event
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(): ViewModel() {

    private val _translucentStatusBar = MutableLiveData<Event<Boolean>>()
    val translucentStatusBar: LiveData<Event<Boolean>> = _translucentStatusBar

    fun setTranslucentStatusBar(showTranslucent: Boolean){
        _translucentStatusBar.value = Event(showTranslucent)
    }
}