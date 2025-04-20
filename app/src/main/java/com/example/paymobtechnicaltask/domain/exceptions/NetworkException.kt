package com.example.paymobtechnicaltask.domain.exceptions

sealed class NetworkExceptions : Exception() {
    data object UnknownHostException : NetworkExceptions()
    data object TimeoutException : NetworkExceptions()
    data object ConnectionException : NetworkExceptions()
    data class HttpException(val msg: String) : NetworkExceptions()
    data object UnknownException: NetworkExceptions()
}