package com.example.paymobtechnicaltask.data.remote

import com.example.paymobtechnicaltask.data.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val moviesService: MoviesService
): RemoteDataSource {

}