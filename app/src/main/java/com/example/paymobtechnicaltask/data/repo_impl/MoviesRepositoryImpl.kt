package com.example.paymobtechnicaltask.data.repo_impl

import com.example.paymobtechnicaltask.data.datasource.LocalDataSource
import com.example.paymobtechnicaltask.data.datasource.RemoteDataSource
import com.example.paymobtechnicaltask.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): MoviesRepository {

}