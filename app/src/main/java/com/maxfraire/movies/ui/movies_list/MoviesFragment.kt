package com.maxfraire.movies.ui.movies_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.MoviesFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.ui.models.MovieListTypeUI
import com.maxfraire.movies.util.Constants.SHARED_AXIS_X_DURATION
import com.maxfraire.movies.util.EventObserver

class MoviesFragment : BaseFragment<MoviesFragmentBinding>() {
    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }
    private lateinit var popularMoviesAdapter: MoviesListAdapter
    private lateinit var upcomingMoviesAdapter: MoviesListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        setUpLists()

        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularMoviesAdapter.submitList(it)
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            upcomingMoviesAdapter.submitList(it)
        }

        viewModel.navigateToSeeAll.observe(
            viewLifecycleOwner,
            EventObserver(onEventUnhandledContent = {
                exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
                    duration = SHARED_AXIS_X_DURATION
                }
                reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
                    duration = SHARED_AXIS_X_DURATION
                }
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToSeeAllMoviesFragment(it)
                )
            })
        )

        viewModel.navigateToMovieDetails.observe(
            viewLifecycleOwner,
            EventObserver(onEventUnhandledContent = {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(it)
                )
            })
        )

    }

    override fun onResume() {
        super.onResume()
        exitTransition = null
        reenterTransition = null
    }

    private fun setUpLists() {
        popularMoviesAdapter = MoviesListAdapter(viewModel, MovieListTypeUI.Popular)
        binding.rvPopularMovies.adapter = popularMoviesAdapter
        binding.rvPopularMovies.setHasFixedSize(true)

        upcomingMoviesAdapter = MoviesListAdapter(viewModel, MovieListTypeUI.Upcoming)
        binding.rvUpcoming.adapter = upcomingMoviesAdapter
        binding.rvUpcoming.setHasFixedSize(true)
    }

    override val layoutResId: Int
        get() = R.layout.movies_fragment

}