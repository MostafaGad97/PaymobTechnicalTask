package com.example.paymobtechnicaltask.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.paymobtechnicaltask.R
import com.example.paymobtechnicaltask.databinding.FragmentMovieDetailsBinding
import com.example.paymobtechnicaltask.domain.model.MovieDetailsModel
import com.example.paymobtechnicaltask.domain.utils.DataState
import com.example.paymobtechnicaltask.ui.base.BaseFragment
import com.example.paymobtechnicaltask.ui.utils.getMoviePosterUrl
import com.example.paymobtechnicaltask.ui.utils.loadImageFromUrl
import com.example.paymobtechnicaltask.ui.utils.showErrorMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) {

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)

        movieDetailsObserver()

        handleClicks()
        getMovieDetails()
    }

    private fun handleClicks() {
        with(binding) {
            btnFavorite.setOnClickListener {
                btnFavorite.isSelected = !btnFavorite.isSelected
                viewModel.toggleFavorite(
                    movieId = args.movieId,
                    isFavorite = btnFavorite.isSelected
                )
            }
        }
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId = args.movieId)
    }

    private fun movieDetailsObserver() {
        lifecycleScope.launch {
            viewModel.movieDetailsResponse.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collectLatest { dataState ->
                when(dataState) {
                    is DataState.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    is DataState.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMessage(dataState.exception)
                    }
                    is DataState.Success -> {
                        binding.progressBar.isVisible = false
                        fillMovieDetails(dataState.data)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun fillMovieDetails(model: MovieDetailsModel) {
        with(binding) {
            ivPoster.loadImageFromUrl(getMoviePosterUrl(model.poster))
            btnFavorite.isSelected = model.isFavorite
            tvName.text = model.title
            tvReleaseDate.text = getString(R.string.release_data_s, model.releaseDate)
            tvLanguage.text = getString(R.string.language_s, model.language)
            tvAverageRate.text = model.voteAverage.toString()
            ratingBar.rating = model.voteAverage / 2
            tvOverview.text = model.overview
        }
    }

}