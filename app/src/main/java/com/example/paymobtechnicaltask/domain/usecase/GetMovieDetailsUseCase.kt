package com.example.paymobtechnicaltask.domain.usecase

import com.example.paymobtechnicaltask.domain.model.MovieDetailsModel
import com.example.paymobtechnicaltask.domain.repository.MoviesRepository
import com.example.paymobtechnicaltask.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(movieId: Int): Flow<DataState<MovieDetailsModel>> = flow {
        emitAll(moviesRepository.getMovieDetails(movieId))
    }
}