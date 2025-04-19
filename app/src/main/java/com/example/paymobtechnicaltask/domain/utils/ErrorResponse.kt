package com.example.paymobtechnicaltask.domain.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status_code") val statusCode: Int? = 0,
    @SerialName("status_message") val statusMessage: String? = ""
)
