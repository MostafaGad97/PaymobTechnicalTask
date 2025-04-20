package com.example.paymobtechnicaltask.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.paymobtechnicaltask.data.local.entity.MovieEntity

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Query("INSERT INTO movies (id) VALUES (:id)")
    suspend fun addToFavorites(id: Int)

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun removeFromFavorites(id: Int)
}