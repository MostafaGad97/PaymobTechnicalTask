package com.example.paymobtechnicaltask.domain.usecase

import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.domain.repository.MoviesRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(movie: Movie) {
        if(movie.isFavorite) {
            moviesRepository.addToFavorites(movie.id)
        } else {
            moviesRepository.removeFromFavorites(movie.id)
        }
    }
}