package com.example.paymobtechnicaltask.ui.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun getMoviePosterUrl(posterPath: String): String {
    return "https://image.tmdb.org/t/p/w500${posterPath}"
}