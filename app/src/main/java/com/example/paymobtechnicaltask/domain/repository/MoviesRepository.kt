package com.example.paymobtechnicaltask.domain.repository

import androidx.paging.PagingData
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.domain.model.MovieDetailsModel
import com.example.paymobtechnicaltask.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): Flow<DataState<MovieDetailsModel>>

    suspend fun addToFavorites(movieId: Int)
    suspend fun removeFromFavorites(movieId: Int)
}