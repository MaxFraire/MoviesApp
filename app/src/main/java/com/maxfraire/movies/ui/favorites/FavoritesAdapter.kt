package com.maxfraire.movies.ui.favorites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.SeeAllMoviesTypeGridItemBinding
import com.maxfraire.movies.databinding.SeeAllMoviesTypeListItemBinding
import com.maxfraire.movies.ui.models.MovieUI
import com.maxfraire.movies.ui.see_all.GridViewHolder
import com.maxfraire.movies.ui.see_all.ListViewHolder

class FavoritesAdapter(
    private val viewModel: FavoritesViewModel
) : ListAdapter<MovieUI, RecyclerView.ViewHolder>(MOVIE_COMPARATOR) {
    private var viewType = R.layout.see_all_movies_type_grid_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.see_all_movies_type_list_item ->
                ListViewHolder(
                    SeeAllMoviesTypeListItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).apply {
                        viewModel = this@FavoritesAdapter.viewModel
                    }
                )
            else ->
                GridViewHolder(
                    SeeAllMoviesTypeGridItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ).apply {
                        viewModel = this@FavoritesAdapter.viewModel
                    }
                )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (holder) {
                is GridViewHolder -> holder.bind(it)
                is ListViewHolder -> holder.bind(it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = viewType

    fun setViewType(@LayoutRes viewType: Int) {
        this.viewType = viewType
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