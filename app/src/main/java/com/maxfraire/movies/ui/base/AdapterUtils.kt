package com.maxfraire.movies.ui.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.maxfraire.movies.ui.models.MovieUI

object AdapterUtils {
    val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieUI>() {
        override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean =
            oldItem == newItem
    }
}