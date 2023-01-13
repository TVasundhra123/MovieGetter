package com.example.moviegetter.recyclerview

data class MovieResponse(
    val Search: List<Movies>
)
data class Movies(
    val Title: String,
    val Poster: String,
    val imdbID: String
)

data class MovieDetails(
    val Title: String,
    val Genre: String,
    val Plot: String
)