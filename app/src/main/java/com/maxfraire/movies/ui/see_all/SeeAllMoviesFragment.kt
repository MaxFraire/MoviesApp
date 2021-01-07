package com.maxfraire.movies.ui.see_all

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.transition.MaterialFadeThrough
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.SeeAllMoviesFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.util.EventObserver
import com.maxfraire.movies.util.GridSpacingDecoration
import com.maxfraire.movies.util.dp
import kotlinx.coroutines.launch

class SeeAllMoviesFragment : BaseFragment<SeeAllMoviesFragmentBinding>() {
    private val viewModel: SeeAllMoviesViewModel by viewModels { viewModelFactory }
    private val args: SeeAllMoviesFragmentArgs by navArgs()
    private val gridItemDecorator = GridSpacingDecoration(GRID_SPAN_COUNT, 8.dp, true)
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManger: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        viewModel.setListType(args.moviesListType)

        val adapter = SeeAllMoviesAdapter(viewModel)
        binding.rvSeeAllMovies.adapter = adapter

        viewModel.movies.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

        gridLayoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
        linearLayoutManger = LinearLayoutManager(context)
        viewModel.displayGridLayout.observe(viewLifecycleOwner){ showGridLayout ->
            TransitionManager.beginDelayedTransition(binding.root as ViewGroup, MaterialFadeThrough())
            adapter.setViewType(
                if (showGridLayout) R.layout.see_all_movies_type_grid_item
                else R.layout.see_all_movies_type_list_item
            )
            binding.rvSeeAllMovies.layoutManager =
                if (showGridLayout) gridLayoutManager
                else linearLayoutManger

            if(showGridLayout) binding.rvSeeAllMovies.addItemDecoration(gridItemDecorator)
            else binding.rvSeeAllMovies.removeItemDecoration(gridItemDecorator)

        }

        viewModel.navigateBack.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })

        viewModel.navigateToMovieDetails.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                SeeAllMoviesFragmentDirections.actionSeeAllMoviesFragmentToMovieDetailsFragment(it)
            )
        })

    }

    companion object {
        private const val GRID_SPAN_COUNT = 3
    }

    override val layoutResId: Int
        get() = R.layout.see_all_movies_fragment
}