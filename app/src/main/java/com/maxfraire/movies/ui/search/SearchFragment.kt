package com.maxfraire.movies.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.maxfraire.movies.R
import com.maxfraire.movies.databinding.SearchFragmentBinding
import com.maxfraire.movies.ui.base.BaseFragment
import com.maxfraire.movies.util.EventObserver
import kotlinx.coroutines.launch
import java.util.*

class SearchFragment : BaseFragment<SearchFragmentBinding>() {

    val viewModel: SearchViewModel by viewModels { viewModelFactory }
    private lateinit var adapter: SearchAdapter
    private lateinit var textWatcher: TextWatcher

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        adapter = SearchAdapter(viewModel)
        binding.rvSearch.adapter = adapter

        viewModel.searchResult.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        viewModel.navigateBack.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigateUp()
        })

        viewModel.navigateToMovieDetails.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(it)
            )
        })

        textWatcher = object : TextWatcher {
            var timer: Timer = Timer()
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer.cancel()
            }

            override fun afterTextChanged(text: Editable?) {
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        viewModel.search(text.toString())
                    }
                }, 500)

            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.tvNoResult.isVisible =
                loadState.source.refresh is LoadState.NotLoading &&
                        adapter.itemCount == 0 &&
                        viewModel.searchQuery.value.orEmpty().isNotEmpty()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.etSearch.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.etSearch.removeTextChangedListener(textWatcher)
    }

    override val layoutResId: Int
        get() = R.layout.search_fragment
}
