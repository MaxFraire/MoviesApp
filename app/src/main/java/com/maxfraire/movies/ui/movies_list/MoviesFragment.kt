package com.maxfraire.movies.ui.movies_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.MoviesFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.ui.models.MovieListTypeUI
import com.maxfraire.movies.util.EventObserver

class MoviesFragment : BaseFragment<MoviesFragmentBinding>() {
    private val viewModel: MoviesViewModel by viewModels { viewModelFactory }
    private lateinit var popularMoviesAdapter: MoviesListAdapter
    private lateinit var upcomingMoviesAdapter: MoviesListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        popularMoviesAdapter = MoviesListAdapter(viewModel, MovieListTypeUI.Popular)
        upcomingMoviesAdapter = MoviesListAdapter(viewModel, MovieListTypeUI.Upcoming)
        binding.rvPopularMovies.adapter = popularMoviesAdapter
        binding.rvUpcoming.adapter = upcomingMoviesAdapter

        viewModel.popularMovies.observe(viewLifecycleOwner) {
            popularMoviesAdapter.submitList(it)
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            upcomingMoviesAdapter.submitList(it)
        }

        viewModel.navigateToSeeAll.observe(
            viewLifecycleOwner,
            EventObserver(onEventUnhandledContent = {
                findNavController().navigate(
                    MoviesFragmentDirections.actionMoviesFragmentToSeeAllMoviesFragment(it)
                )
            })
        )

    }

    override val layoutResId: Int
        get() = R.layout.movies_fragment

}