package com.example.paymobtechnicaltask.data.datasource

import androidx.paging.PagingSource
import com.example.paymobtechnicaltask.data.remote.dto.MovieDetailsDto
import com.example.paymobtechnicaltask.data.remote.dto.MovieDto

interface RemoteDataSource {
    fun getMovies(): PagingSource<Int, MovieDto>
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto
}