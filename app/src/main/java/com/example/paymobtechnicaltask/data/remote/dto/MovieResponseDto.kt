package com.example.paymobtechnicaltask.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
    val page: Int? = 0,
    val results: List<MovieDto>? = null,
    @SerialName("total_pages") val totalPages: Int? = 0,
    @SerialName("total_results") val totalResults: Int? = 0,
)
