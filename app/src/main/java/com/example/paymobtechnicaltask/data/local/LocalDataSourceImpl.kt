package com.example.paymobtechnicaltask.data.local

import com.example.paymobtechnicaltask.data.datasource.LocalDataSource
import com.example.paymobtechnicaltask.data.local.entity.MovieEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao
): LocalDataSource {

    override suspend fun getFavoriteMovies(): List<MovieEntity> {
        return moviesDao.getFavoriteMovies()
    }

    override suspend fun addToFavorites(id: Int) {
        moviesDao.addToFavorites(id)
    }

    override suspend fun removeFromFavorites(id: Int) {
        moviesDao.removeFromFavorites(id)
    }
}