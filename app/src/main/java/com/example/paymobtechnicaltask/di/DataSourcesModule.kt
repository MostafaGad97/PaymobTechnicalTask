package com.example.paymobtechnicaltask.di

import com.example.paymobtechnicaltask.data.datasource.LocalDataSource
import com.example.paymobtechnicaltask.data.datasource.RemoteDataSource
import com.example.paymobtechnicaltask.data.local.LocalDataSourceImpl
import com.example.paymobtechnicaltask.data.local.MoviesDao
import com.example.paymobtechnicaltask.data.remote.MoviesService
import com.example.paymobtechnicaltask.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourcesModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        moviesService: MoviesService
    ): RemoteDataSource = RemoteDataSourceImpl(moviesService)

    @Provides
    @Singleton
    fun provideLocalDataSource(
        moviesDao: MoviesDao
    ): LocalDataSource = LocalDataSourceImpl(moviesDao)

}