package com.maxfraire.movies.ui.see_all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.MoviesTypeGridItemBinding
import com.maxfraire.movies.databinding.MoviesTypeListItemBinding
import com.maxfraire.movies.ui.base.AdapterUtils
import com.maxfraire.movies.ui.models.MovieUI

class SeeAllMoviesAdapter(
    private val viewModel: SeeAllMoviesViewModel
) : PagingDataAdapter<MovieUI, RecyclerView.ViewHolder>(AdapterUtils.MOVIE_COMPARATOR) {
    private var viewType = R.layout.movies_type_grid_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.movies_type_list_item ->
                ListViewHolder(
                    MoviesTypeListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).apply {
                        viewModel = this@SeeAllMoviesAdapter.viewModel
                    }
                )
            else ->
                GridViewHolder(
                    MoviesTypeGridItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).apply {
                        viewModel = this@SeeAllMoviesAdapter.viewModel
                    }
                )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when(holder){
                is GridViewHolder-> holder.bind(it)
                is ListViewHolder -> holder.bind(it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = viewType

    fun setViewType(@LayoutRes viewType: Int){
        this.viewType = viewType
    }
}

class GridViewHolder(private val binding: MoviesTypeGridItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieUI) {
        binding.movie = movie
    }
}

class ListViewHolder(private val binding: MoviesTypeListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieUI) {
        binding.movie = movie
    }
}

