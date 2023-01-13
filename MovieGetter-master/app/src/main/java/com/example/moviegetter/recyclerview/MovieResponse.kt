package com.example.moviegetter.recyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class MovieResponse(
    val Search: List<Movies>
)
@Parcelize
data class Movies(
    val Title: String,
    val Poster: String,
    val imdbID: String,
    val Year: String,
    val Type: String
) : Parcelable

data class MovieDetails(
    val Title: String,
    val Genre: String,
    val Plot: String
)