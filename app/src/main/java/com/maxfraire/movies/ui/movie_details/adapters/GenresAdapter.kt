package com.maxfraire.movies.ui.movie_details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxfraire.movies.databinding.GenreListItemBinding
import com.maxfraire.movies.ui.models.GenreUI

class GenresAdapter : ListAdapter<GenreUI, ViewHolder>(
    GENRES_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            GenreListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val GENRES_COMPARATOR = object : DiffUtil.ItemCallback<GenreUI>() {
            override fun areItemsTheSame(oldItem: GenreUI, newItem: GenreUI): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: GenreUI, newItem: GenreUI): Boolean =
                oldItem.id == newItem.id

        }
    }
}

class ViewHolder(val binding: GenreListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(genre: GenreUI) {
        binding.genre = genre
    }
}