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

fun <T> handleSuccess(response: T): DataState<T> {
    if (response != null) return DataState.Success(response)
    return DataState.Error(NetworkExceptions.UnknownException)
}

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
