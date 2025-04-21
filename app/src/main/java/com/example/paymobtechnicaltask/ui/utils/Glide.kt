package com.example.paymobtechnicaltask.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

/**
 * Constructs the full URL for a movie poster from a given poster path.
 *
 * @param posterPath The relative path returned by the API (e.g., "/abc123.jpg")
 * @return A full URL string to fetch the poster image
 */

fun getMoviePosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/w500${posterPath}"
}