package com.example.moviegetter.network

class MoviesRepository(private val apiService: RetrofitApi, private val tag: String) {
    suspend fun fetchMovies() = apiService.getMoviesData(tag)
}