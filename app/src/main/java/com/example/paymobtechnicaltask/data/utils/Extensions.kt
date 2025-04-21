package com.example.paymobtechnicaltask.data.utils

import java.util.Locale

/**
 * Rounds the float to one decimal place.
 *
 * Example:
 * 3.14159f.roundToOneDecimalNumber() -> 3.1f
 *
 * @return Float rounded to 1 decimal place
 */
fun Float.roundToOneDecimalNumber(): Float {
    return String.format(Locale.US, "%.1f", this).toFloat()
}