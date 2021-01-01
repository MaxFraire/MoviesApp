package com.maxfraire.movies.ui.see_all

import android.content.res.Resources
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.SeeAllMoviesFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.util.GridSpacingDecoration
import com.maxfraire.movies.util.dp
import kotlinx.coroutines.launch

class SeeAllMoviesFragment : BaseFragment<SeeAllMoviesFragmentBinding>() {
    private val viewModel: SeeAllMoviesViewModel by viewModels { viewModelFactory }
    private val args: SeeAllMoviesFragmentArgs by navArgs()
    private val linearLayoutManger = LinearLayoutManager(context)
    private val gridLayoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
    private val gridItemDecorator = GridSpacingDecoration(GRID_SPAN_COUNT, 8.dp, true)

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

        viewModel.displayGridLayout.observe(viewLifecycleOwner) { showGridLayout ->
            TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
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

        viewModel.onBackPressed.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    companion object {
        private const val GRID_SPAN_COUNT = 3
    }

    override val layoutResId: Int
        get() = R.layout.see_all_movies_fragment
}