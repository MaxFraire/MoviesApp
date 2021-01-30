package com.maxfraire.movies.ui.movies_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.MovieListItemBinding
import com.maxfraire.movies.databinding.MovieListItemSeeAllBinding
import com.maxfraire.movies.ui.base.AdapterUtils
import com.maxfraire.movies.ui.models.MovieListTypeUI
import com.maxfraire.movies.ui.models.MovieUI

class MoviesListAdapter(
    private val viewModel: MoviesViewModel,
    private val listType: MovieListTypeUI
) : ListAdapter<MovieUI, RecyclerView.ViewHolder>(AdapterUtils.MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.movie_list_item_see_all ->
                SeeAllViewHolder(
                    MovieListItemSeeAllBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).apply {
                        viewModel = this@MoviesListAdapter.viewModel
                        listType = this@MoviesListAdapter.listType
                    }
                )
            else ->
                MovieListViewHolder(
                    MovieListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).apply {
                        viewModel = this@MoviesListAdapter.viewModel
                    }
                )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is MovieListViewHolder -> holder.bind(getItem(position))
            is SeeAllViewHolder ->  {}
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position == itemCount.dec())
            R.layout.movie_list_item_see_all
        else
            R.layout.movie_list_item

}

class MovieListViewHolder(private val binding: MovieListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieUI){
        binding.movie = movie
    }
}

class SeeAllViewHolder(binding: MovieListItemSeeAllBinding)
    : RecyclerView.ViewHolder(binding.root)

