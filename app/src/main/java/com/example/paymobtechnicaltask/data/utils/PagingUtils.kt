package com.example.paymobtechnicaltask.data.utils

import androidx.paging.PagingSource
import com.example.paymobtechnicaltask.data.remote.ErrorResponse
import com.example.paymobtechnicaltask.domain.exceptions.NetworkExceptions
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Handles exceptions thrown during a Paging 3 data load and maps them
 * to appropriate [PagingSource.LoadResult.Error] with custom exceptions.
 *
 * @param exception The exception thrown during paging
 * @return A LoadResult error containing a custom or raw exception
 */
fun <T: Any> handlePagingError(exception: Exception) : PagingSource.LoadResult<Int, T> {
    return when(exception) {
        is UnknownHostException -> PagingSource.LoadResult.Error(NetworkExceptions.UnknownHostException)
        is IOException -> PagingSource.LoadResult.Error(NetworkExceptions.ConnectionException)
        is TimeoutException -> PagingSource.LoadResult.Error(NetworkExceptions.TimeoutException)
        is HttpException -> {
            try {
                val errorBody = exception.response()?.errorBody()?.string()
                if(!errorBody.isNullOrEmpty()) {
                    val errorMsg = errorBody.fromJson<ErrorResponse>().statusMessage
                    PagingSource.LoadResult.Error(NetworkExceptions.HttpException(errorMsg))
                }
                else {
                    PagingSource.LoadResult.Error(NetworkExceptions.HttpException(exception.message()))
                }
            } catch (e: Exception) {
                PagingSource.LoadResult.Error(e)
            }
        }
        else -> PagingSource.LoadResult.Error(exception)
    }
}