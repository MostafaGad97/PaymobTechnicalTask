package com.example.paymobtechnicaltask.data.utils

import kotlinx.serialization.json.Json

val json = Json { ignoreUnknownKeys = true }

inline fun <reified T : Any> String.fromJson(): T =
    json.decodeFromString(this)