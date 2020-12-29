package com.maxfraire.movies.ui.see_all

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.maxfraire.movies.databinding.SeeAllMoviesListItemBinding
import com.maxfraire.movies.ui.models.MovieUI

class SeeAllMoviesAdapter(
    private val viewModel: SeeAllMoviesViewModel
) : PagingDataAdapter<MovieUI, ViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            SeeAllMoviesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
                viewModel = this@SeeAllMoviesAdapter.viewModel
            }
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieUI>() {
            override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean =
                oldItem == newItem
        }
    }
}

class ViewHolder(private val binding: SeeAllMoviesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieUI) {
        binding.movie = movie
    }
}

