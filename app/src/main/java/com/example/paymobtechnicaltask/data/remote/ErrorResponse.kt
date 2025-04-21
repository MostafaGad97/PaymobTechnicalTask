package com.example.paymobtechnicaltask.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status_message") val statusMessage: String = ""
)
