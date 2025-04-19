package com.example.paymobtechnicaltask.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val posterPath: String = "",
    val title: String = "",
    val releaseDate: String = "",
    val voteAverage: Float = 0f,
    var isFavorite: Boolean = false,
)
