package com.example.paymobtechnicaltask.data.repo_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.paymobtechnicaltask.data.datasource.LocalDataSource
import com.example.paymobtechnicaltask.data.datasource.RemoteDataSource
import com.example.paymobtechnicaltask.data.remote.dto.MovieDetailsDto.Companion.toMovieDetailsModel
import com.example.paymobtechnicaltask.data.remote.dto.MovieDto.Companion.toMovie
import com.example.paymobtechnicaltask.data.utils.safeApiCall
import com.example.paymobtechnicaltask.domain.model.Movie
import com.example.paymobtechnicaltask.domain.model.MovieDetailsModel
import com.example.paymobtechnicaltask.domain.repository.MoviesRepository
import com.example.paymobtechnicaltask.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MoviesRepository {

    override suspend fun getMovies(): Flow<PagingData<Movie>> {
        val favoriteIds = localDataSource.getFavoriteMovies().map { it.id }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                remoteDataSource.getMovies()
            }
        ).flow.map { pagingData ->
            pagingData.map { movieDto ->
                movieDto.toMovie().copy(isFavorite = favoriteIds.contains(movieDto.id))
            }
        }
    }

    override suspend fun getMovieDetails(movieId: Int) = safeApiCall {
        val isFavorite = localDataSource.getFavoriteMovies().any { it.id == movieId}
        remoteDataSource.getMovieDetails(movieId).toMovieDetailsModel().copy(isFavorite = isFavorite)
    }

    // Local
    override suspend fun addToFavorites(id: Int) {
        localDataSource.addToFavorites(id)
    }

    override suspend fun removeFromFavorites(id: Int) {
        localDataSource.removeFromFavorites(id)
    }
}