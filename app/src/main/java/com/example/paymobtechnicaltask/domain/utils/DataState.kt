package com.example.paymobtechnicaltask.domain.utils

sealed class DataState<out T> {
    data object Idle : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
    data class Success<out R>(val data: R) : DataState<R>()
    data class Error(val exception: Throwable) : DataState<Nothing>()
}