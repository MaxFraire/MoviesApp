package com.maxfraire.movies.ui.favorites

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFadeThrough
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.FavoritesFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.util.EventObserver
import com.maxfraire.movies.util.GridSpacingDecoration
import com.maxfraire.movies.util.dp

class FavoritesFragment : BaseFragment<FavoritesFragmentBinding>() {

    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }
    private val gridItemDecorator = GridSpacingDecoration(GRID_SPAN_COUNT, 8.dp, true)
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManger: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        val adapter = FavoritesAdapter(viewModel)
        binding.rvFavorites.adapter = adapter

        viewModel.favorites.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        gridLayoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
        linearLayoutManger = LinearLayoutManager(context)
        viewModel.displayGridLayout.observe(viewLifecycleOwner) { showGridLayout ->
            TransitionManager.beginDelayedTransition(
                binding.root as ViewGroup,
                MaterialFadeThrough()
            )
            adapter.setViewType(
                if (showGridLayout) R.layout.movies_type_grid_item
                else R.layout.movies_type_list_item
            )
            binding.rvFavorites.layoutManager =
                if (showGridLayout) gridLayoutManager
                else linearLayoutManger

            if (showGridLayout) binding.rvFavorites.addItemDecoration(gridItemDecorator)
            else binding.rvFavorites.removeItemDecoration(gridItemDecorator)

        }

        viewModel.navigateBack.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })

        viewModel.navigateToMovieDetails.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToMovieDetailsFragment(it)
            )
        })
    }

    companion object {
        private const val GRID_SPAN_COUNT = 3
    }

    override val layoutResId: Int
        get() = R.layout.favorites_fragment
}