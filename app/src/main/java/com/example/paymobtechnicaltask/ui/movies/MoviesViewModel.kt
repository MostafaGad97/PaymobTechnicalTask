package com.example.paymobtechnicaltask.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.domain.usecase.GetMoviesUseCase
import com.example.paymobtechnicaltask.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
): ViewModel() {

    private val _moviesResponse = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val moviesResponse = _moviesResponse.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase().cachedIn(viewModelScope).collectLatest {
                _moviesResponse.value = it
            }
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            toggleFavoriteUseCase.invoke(
                movieId = movie.id,
                isFavorite = movie.isFavorite
            )
        }
    }
}