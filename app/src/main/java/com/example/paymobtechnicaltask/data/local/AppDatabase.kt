package com.example.paymobtechnicaltask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paymobtechnicaltask.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun MoviesDao(): MoviesDao

}