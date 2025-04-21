package com.example.paymobtechnicaltask.data.remote

import com.example.paymobtechnicaltask.data.remote.dto.MovieDetailsDto
import com.example.paymobtechnicaltask.data.remote.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("primary_release_year") releaseDate: Int = 2024
    ): MovieResponseDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): MovieDetailsDto
}