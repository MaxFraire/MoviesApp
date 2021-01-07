package com.maxfraire.movies.ui.movie_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.MovieDetailsFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.ui.main_activity.MainActivityViewModel
import com.maxfraire.movies.ui.models.CastUI
import com.maxfraire.movies.ui.models.GenreUI
import com.maxfraire.movies.util.EventObserver
import timber.log.Timber

class MovieDetailsFragment : BaseFragment<MovieDetailsFragmentBinding>() {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }
    private val activityViewModel: MainActivityViewModel by activityViewModels { viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewModel.setMovieId(args.movie)
        viewModel.movie.observe(viewLifecycleOwner) {
            binding.movie = it
            setGenres(it.genres)
            setCast(it.cast)
        }

        viewModel.onBackPressed.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })

        binding.collapsingToolbar.setExpandedTitleColor(
            ContextCompat.getColor(requireContext(), android.R.color.transparent)
        )
    }

    private fun setGenres(genres: List<GenreUI>) {
        binding.rvGenres.apply {
            layoutManager =  FlexboxLayoutManager(
                context,
                FlexDirection.ROW,
                FlexWrap.WRAP
            )
            adapter = GenresAdapter().apply {
                submitList(genres)
            }
        }
    }

    private fun setCast(cast: List<CastUI>) {
        binding.rvCast.apply {
            adapter = CastAdapter().apply {
                submitList(cast)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.setTranslucentStatusBar(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        activityViewModel.setTranslucentStatusBar(false)
    }

    override val layoutResId: Int
        get() = R.layout.movie_details_fragment

}