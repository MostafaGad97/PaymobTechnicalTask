package com.example.paymobtechnicaltask.ui.movies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.paymobtechnicaltask.databinding.FragmentMoviesBinding
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.ui.base.BaseFragment
import com.example.paymobtechnicaltask.ui.movies.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val viewModel: MoviesViewModel by viewModels()
    private val moviesAdapter by lazy { MoviesAdapter(::onFavoriteClicked) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMoviesObserver()
        handlePagingResponse()

        initRv()
        getMovies()
    }

    private fun initRv() {
        moviesAdapter.refresh()
        binding.rvMovies.adapter = moviesAdapter
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

    private fun handlePagingResponse(){
        lifecycleScope.launch {
            moviesAdapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.CREATED).collectLatest {
                when(it.refresh){
                    is LoadState.Loading -> {
                        //onLoad(true)
                    }
                    is LoadState.Error -> {
                        //onLoad(false)
                        val error = (it.refresh as LoadState.Error).error
                        //val state = DataState.Error(error) )
                    }
                    is LoadState.NotLoading -> {
                        //onLoad(false)
                        //handleEmptyData(favoritesAdapter.itemCount < 1)
                    }
                }
            }
        }
    }

    private fun onFavoriteClicked(movie: Movie) {
        viewModel.toggleFavorite(movie)
    }
}