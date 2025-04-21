package com.example.paymobtechnicaltask.data.remote.dto

import com.example.paymobtechnicaltask.data.utils.roundToOneDecimalNumber
import com.example.paymobtechnicaltask.domain.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int? = 0,
    @SerialName("poster_path") val posterPath: String? = "",
    @SerialName("title") val title: String? = "",
    @SerialName("original_title") val originalTitle: String? = "",
    @SerialName("release_date") val releaseDate: String? = "",
    @SerialName("vote_average") val voteAverage: Float? = 0f,
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = this.id ?: 0,
        title = this.title ?: "",
        releaseDate = this.releaseDate ?: "",
        posterPath = this.posterPath ?: "",
        voteAverage = this.voteAverage?.roundToOneDecimalNumber() ?: 0f,
    )
}
