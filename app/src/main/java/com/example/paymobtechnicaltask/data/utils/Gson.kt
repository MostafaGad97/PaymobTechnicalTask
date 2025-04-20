package com.example.paymobtechnicaltask.data.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

val json = Json { ignoreUnknownKeys = true }

inline fun <reified T : Any> String.fromJson(): T =
    json.decodeFromString(this)