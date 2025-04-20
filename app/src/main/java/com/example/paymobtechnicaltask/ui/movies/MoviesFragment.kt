package com.example.paymobtechnicaltask.ui.movies

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.paymobtechnicaltask.databinding.FragmentMoviesBinding
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.ui.base.BaseFragment
import com.example.paymobtechnicaltask.ui.movies.adapter.MovieLoadStateAdapter
import com.example.paymobtechnicaltask.ui.movies.adapter.MoviesAdapter
import com.example.paymobtechnicaltask.ui.utils.showErrorMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()

    private val moviesAdapter by lazy {
        MoviesAdapter(::onFavoriteClicked)
    }

    private val footerAdapter by lazy {
        MovieLoadStateAdapter { moviesAdapter.retry() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMoviesObserver()
        handlePagingResponse()

        initRv()
        getMovies()

        handleClicks()
    }

    private fun handleClicks() {
        with(binding) {
            layoutEmpty.btnRetry.setOnClickListener {
                getMovies()
                layoutEmpty.root.isVisible = false
            }
        }
    }

    private fun initRv() {
        moviesAdapter.refresh()

        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == moviesAdapter.itemCount) 2 else 1
            }
        }

        binding.rvMovies.layoutManager = gridLayoutManager
        binding.rvMovies.adapter = moviesAdapter.withLoadStateFooter(footerAdapter)
    }

    private fun getMovies() {
        viewModel.getMovies()
    }

    private fun getMoviesObserver() {
        lifecycleScope.launch {
            viewModel.moviesResponse.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collectLatest { movies ->
                moviesAdapter.submitData(lifecycle, movies)
            }
        }
    }

    private fun handlePagingResponse() {
        lifecycleScope.launch {
            moviesAdapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> {}

                    is LoadState.Error -> {
                        val error = (it.refresh as LoadState.Error).error
                        showErrorMessage(error)
                        if(moviesAdapter.itemCount < 1) {
                            handleErrorLayout()
                        }
                    }

                    is LoadState.NotLoading -> {
                        if(moviesAdapter.itemCount < 1) {
                            handleErrorLayout()
                        }
                    }
                }
            }
        }
    }

    private fun handleErrorLayout() {
        binding.layoutEmpty.root.isVisible = true
    }

    private fun onFavoriteClicked(movie: Movie) {
        viewModel.toggleFavorite(movie)
    }
}