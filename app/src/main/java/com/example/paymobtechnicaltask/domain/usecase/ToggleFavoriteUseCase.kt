package com.example.paymobtechnicaltask.domain.usecase

import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.domain.repository.MoviesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int, isFavorite: Boolean) {
        if(isFavorite) {
            moviesRepository.addToFavorites(movieId)
        } else {
            moviesRepository.removeFromFavorites(movieId)
        }
    }
}