package com.example.paymobtechnicaltask.domain.usecase

import androidx.paging.PagingData
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Movie>> {
        return moviesRepository.getMovies()
    }
}