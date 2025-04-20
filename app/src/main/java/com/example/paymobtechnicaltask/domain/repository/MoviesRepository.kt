package com.example.paymobtechnicaltask.domain.repository

import androidx.paging.PagingData
import com.example.paymobtechnicaltask.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<PagingData<Movie>>

    suspend fun addToFavorites(id: Int)
    suspend fun removeFromFavorites(id: Int)
}