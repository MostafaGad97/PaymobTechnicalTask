package com.example.paymobtechnicaltask.data.repo_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.paymobtechnicaltask.data.datasource.LocalDataSource
import com.example.paymobtechnicaltask.data.datasource.RemoteDataSource
import com.example.paymobtechnicaltask.data.remote.dto.toMovie
import com.example.paymobtechnicaltask.data.remote.dto.toMovieDetailsModel
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

    /**
     * Retrieves a paginated flow of movies from the remote data source and maps them to domain models.
     * Also marks each movie as favorite if its ID exists in the local favorites database.
     *
     * @return A [Flow] of [PagingData] containing a list of [Movie].
     */
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
            // Map the MovieDto to MovieModel and set the isFavorite flag
            pagingData.map { movieDto ->
                movieDto.toMovie().copy(isFavorite = favoriteIds.contains(movieDto.id))
            }
        }
    }

    /**
     * Retrieves movie details from the remote data source
     * and combines it with local data to determine the favorite status of the movie.
     *
     * @param movieId The ID of the movie.
     * @return A [Flow] of [DataState] containing [MovieDetailsModel], with an `isFavorite` flag.
     */
    override suspend fun getMovieDetails(movieId: Int): Flow<DataState<MovieDetailsModel>> = safeApiCall {
        val isFavorite = localDataSource.getFavoriteMovies().any { it.id == movieId}
        remoteDataSource.getMovieDetails(movieId).toMovieDetailsModel().copy(isFavorite = isFavorite)
    }


    // ------------------ Local --------------------
    override suspend fun addToFavorites(movieId: Int) {
        localDataSource.addToFavorites(movieId)
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        localDataSource.removeFromFavorites(movieId)
    }
}