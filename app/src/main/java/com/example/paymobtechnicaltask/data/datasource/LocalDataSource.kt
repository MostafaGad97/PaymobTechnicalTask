package com.example.paymobtechnicaltask.data.datasource

import com.example.paymobtechnicaltask.data.local.entity.MovieEntity

interface LocalDataSource {
    suspend fun getFavoriteMovies(): List<MovieEntity>

    suspend fun addToFavorites(movieId: Int)
    suspend fun removeFromFavorites(movieId: Int)
}