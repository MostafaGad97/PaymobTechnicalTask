package com.example.paymobtechnicaltask.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.domain.model.MovieDetailsModel
import com.example.paymobtechnicaltask.domain.usecase.GetMovieDetailsUseCase
import com.example.paymobtechnicaltask.domain.usecase.ToggleFavoriteUseCase
import com.example.paymobtechnicaltask.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {

    private val _movieDetailsResponse = MutableStateFlow<DataState<MovieDetailsModel>>(DataState.Idle)
    val movieDetailsResponse = _movieDetailsResponse.asStateFlow()

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collectLatest {
                _movieDetailsResponse.value = it
            }
        }
    }

    fun toggleFavorite(movieId: Int) {
        viewModelScope.launch {
            //toggleFavoriteUseCase.invoke(movie)
        }
    }
}