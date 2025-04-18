package com.example.paymobtechnicaltask.data.local

import com.example.paymobtechnicaltask.data.datasource.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao
): LocalDataSource {

}