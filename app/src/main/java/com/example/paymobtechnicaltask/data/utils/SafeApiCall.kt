package com.example.paymobtechnicaltask.data.utils

import com.example.paymobtechnicaltask.data.remote.ErrorResponse
import com.example.paymobtechnicaltask.domain.exceptions.NetworkExceptions
import com.example.paymobtechnicaltask.domain.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Executes a API call safely and emits the result as a Flow of [DataState].
 *
 * Emits:
 * - [DataState.Loading] initially
 * - [DataState.Success] on success
 * - [DataState.Error] on failure with appropriate error type
 *
 * @param apiCall The API request to perform.
 */

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Flow<DataState<T>> = flow {
    val response = apiCall.invoke()
    emit(handleSuccess(response))
}.onStart {
    emit(DataState.Loading)
}.catch { e ->
    emit(handleError(e))
}


/**
 * Handles a successful API response.
 *
 * @param response The response returned from the API call
 * @return [DataState.Success] if response is not null, otherwise an Error state
 */

fun <T> handleSuccess(response: T): DataState<T> {
    if (response != null) return DataState.Success(response)
    return DataState.Error(NetworkExceptions.UnknownException)
}


/**
 * Maps thrown exceptions to custom [NetworkExceptions] wrapped in [DataState].
 *
 * @param it The thrown exception
 * @return [DataState] with appropriate [NetworkExceptions] type
 */

fun <T> handleError(it: Throwable): DataState<T> {
    return when (it) {
        is UnknownHostException -> {
            DataState.Error(NetworkExceptions.UnknownHostException)
        }

        is IOException -> {
            DataState.Error(NetworkExceptions.ConnectionException)
        }

        is TimeoutException -> {
            DataState.Error(NetworkExceptions.TimeoutException)
        }

        is HttpException -> {
            DataState.Error(handleHttpException(it))
        }

        else -> {
            DataState.Error(NetworkExceptions.UnknownException)
        }
    }
}


/**
 * Parses the [HttpException] error body to [ErrorResponse] and extracts the status message.
 *
 * @param exception The [HttpException] thrown during the API call
 * @return A specific [NetworkExceptions.HttpException] or [NetworkExceptions.UnknownException]
 */

fun handleHttpException(exception: HttpException): NetworkExceptions {
    return try {
        val errorBody = exception.response()?.errorBody()?.string()
        if(!errorBody.isNullOrEmpty()) {
            val errorMsg = errorBody.fromJson<ErrorResponse>().statusMessage
            NetworkExceptions.HttpException(errorMsg)
        }
        else NetworkExceptions.UnknownException
    } catch (e: Exception) {
        NetworkExceptions.UnknownException
    }
}
