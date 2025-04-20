package com.example.paymobtechnicaltask.domain.model

data class MovieDetailsModel(
    val id: Int,
    val language: String,
    val poster: String,
    val title: String,
    val voteAverage: Float,
    val releaseDate: String,
    val overview: String,
    var isFavorite: Boolean = false
)
