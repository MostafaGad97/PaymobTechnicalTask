package com.example.paymobtechnicaltask.data.remote.dto

import com.example.paymobtechnicaltask.data.utils.roundToOneDecimalNumber
import com.example.paymobtechnicaltask.domain.model.MovieDetailsModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsDto(
    val id: Int? = 0,
    @SerialName("original_language") val language: String? = "",
    @SerialName("poster_path") val poster: String? = "",
    val title: String? = "",
    @SerialName("vote_average") val voteAverage: Float? = 0f,
    @SerialName("release_date") val releaseDate: String? = "",
    val overview: String? = "",
)

fun MovieDetailsDto.toMovieDetailsModel(): MovieDetailsModel {
    return MovieDetailsModel(
        id = this.id ?: 0,
        title = this.title ?: "",
        language = this.language ?: "",
        poster = this.poster ?: "",
        voteAverage = this.voteAverage?.roundToOneDecimalNumber() ?: 0f,
        releaseDate = this.releaseDate ?: "",
        overview = this.overview ?: "",
    )
}