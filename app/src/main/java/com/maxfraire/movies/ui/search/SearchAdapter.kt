package com.maxfraire.movies.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxfraire.movies.databinding.MoviesTypeListItemBinding
import com.maxfraire.movies.ui.base.AdapterUtils
import com.maxfraire.movies.ui.models.MovieUI

class SearchAdapter(
    private val viewModel: SearchViewModel
) : PagingDataAdapter<MovieUI, SearchViewHolder>(AdapterUtils.MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            MoviesTypeListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
                viewModel = this@SearchAdapter.viewModel
            }
        )

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

class SearchViewHolder(private val binding: MoviesTypeListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieUI) {
        binding.movie = movie
    }
}


