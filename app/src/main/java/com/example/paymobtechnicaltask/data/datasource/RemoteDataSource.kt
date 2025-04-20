package com.example.paymobtechnicaltask.data.datasource

import androidx.paging.PagingSource
import com.example.paymobtechnicaltask.data.remote.dto.MovieDto

interface RemoteDataSource {
    fun getMovies(): PagingSource<Int, MovieDto>
}