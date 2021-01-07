package com.maxfraire.movies.ui.movie_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxfraire.movies.databinding.CastListItemBinding
import com.maxfraire.movies.ui.models.CastUI

class CastAdapter : ListAdapter<CastUI, CastAdapter.ViewHolder>(CAST_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.ViewHolder =
        ViewHolder(
            CastListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CastAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: CastListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: CastUI) {
            binding.cast = cast
        }
    }

    companion object {
        private val CAST_COMPARATOR = object : DiffUtil.ItemCallback<CastUI>() {
            override fun areItemsTheSame(oldItem: CastUI, newItem: CastUI): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: CastUI, newItem: CastUI): Boolean =
                oldItem.id == newItem.id

        }
    }
}

