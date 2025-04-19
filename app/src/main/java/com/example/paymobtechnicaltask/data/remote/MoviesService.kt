package com.example.paymobtechnicaltask.data.remote

import com.example.paymobtechnicaltask.data.remote.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("primary_release_year") releaseDate: Int = 2024
    ): MovieResponseDto
}