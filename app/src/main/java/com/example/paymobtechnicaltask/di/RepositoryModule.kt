package com.example.paymobtechnicaltask.di

import com.example.paymobtechnicaltask.data.datasource.LocalDataSource
import com.example.paymobtechnicaltask.data.datasource.RemoteDataSource
import com.example.paymobtechnicaltask.data.repo_impl.MoviesRepositoryImpl
import com.example.paymobtechnicaltask.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

}