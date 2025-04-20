package com.example.paymobtechnicaltask.data.utils

import java.util.Locale

fun Float.roundToOneDecimalNumber(): Float {
    return String.format(Locale.US, "%.1f", this).toFloat()
}