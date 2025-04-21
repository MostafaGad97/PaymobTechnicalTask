package com.example.paymobtechnicaltask.ui.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.paymobtechnicaltask.R
import com.example.paymobtechnicaltask.domain.exceptions.NetworkExceptions

/**
 * Extension function to display error messages in a Toast
 * based on the type of NetworkException received.
 *
 * @param throwable The [Throwable] to evaluate and display
 */
fun Fragment.showErrorMessage(throwable: Throwable) {
    val errorMsg = when (throwable) {
        is NetworkExceptions.UnknownHostException -> getString(R.string.no_internet_connection)
        is NetworkExceptions.TimeoutException -> getString(R.string.connection_timeout)
        is NetworkExceptions.ConnectionException -> getString(R.string.network_error_please_check_your_internet_connection)
        is NetworkExceptions.HttpException -> {
            try {
                throwable.msg
            } catch (e: Exception) {
                getString(R.string.unknown_error)
            }
        }
        else -> getString(R.string.unknown_error)
    }
    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
}