package com.maxfraire.movies.ui.movie_details

import android.graphics.drawable.Animatable
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maxfraire.movies.R

object MovieDetailsBindingAdapter {

    @JvmStatic
    @BindingAdapter("isFavorite")
    fun setFavoriteIcon(view: FloatingActionButton, isFavorite: Boolean) {
        view.setImageResource(
            if (isFavorite) R.drawable.avd_favorites else R.drawable.avd_favorites_empty
        )
        (view.drawable as Animatable).start()
    }

}