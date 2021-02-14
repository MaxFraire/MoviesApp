package com.maxfraire.movies.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.transition.MaterialSharedAxis
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.MovieDetailsFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.ui.movie_details.adapters.CastAdapter
import com.maxfraire.movies.ui.movie_details.adapters.GenresAdapter
import com.maxfraire.movies.util.Constants
import com.maxfraire.movies.util.EventObserver

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var castAdapter: CastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = Constants.SHARED_AXIS_X_DURATION
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = Constants.SHARED_AXIS_X_DURATION
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.setMovieId(args.movie)

        setAdapters()

        viewModel.movie.observe(viewLifecycleOwner) {
            genresAdapter.submitList(it.genres)
            castAdapter.submitList(it.cast)
        }

        viewModel.onBackPressed.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })

        binding.collapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(requireContext(), android.R.color.transparent)
        )
    }

    private fun setAdapters() {
        genresAdapter = GenresAdapter()
        castAdapter = CastAdapter()
        binding.rvGenres.apply {
            layoutManager =  FlexboxLayoutManager(
                context,
                FlexDirection.ROW,
                FlexWrap.WRAP
            )
            adapter = genresAdapter
        }

        binding.rvCast.adapter = castAdapter
    }

    override val layoutResId: Int
        get() = R.layout.movie_details_fragment

}