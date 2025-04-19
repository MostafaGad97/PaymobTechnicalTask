package com.example.paymobtechnicaltask.data.remote

import androidx.paging.PagingSource
import com.example.paymobtechnicaltask.data.datasource.RemoteDataSource
import com.example.paymobtechnicaltask.data.remote.dto.MovieDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
): RemoteDataSource {

    override fun getMovies(): PagingSource<Int, MovieDto> {
        return MoviesPagingSource(moviesService)
    }
}