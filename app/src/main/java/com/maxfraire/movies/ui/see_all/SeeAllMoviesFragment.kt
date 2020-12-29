package com.maxfraire.movies.ui.see_all

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.SeeAllMoviesFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import kotlinx.coroutines.launch

class SeeAllMoviesFragment : BaseFragment<SeeAllMoviesFragmentBinding>() {
    private val viewModel: SeeAllMoviesViewModel by viewModels { viewModelFactory }
    private val args: SeeAllMoviesFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.setListType(args.moviesListType)

        val adapter = SeeAllMoviesAdapter(viewModel)
        binding.rvSeeAllMovies.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(context, 3)
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }

    }

    override val layoutResId: Int
        get() = R.layout.see_all_movies_fragment
}