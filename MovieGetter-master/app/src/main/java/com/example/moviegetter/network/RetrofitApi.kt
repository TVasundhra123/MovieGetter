package com.example.moviegetter.network

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("/")
    suspend  fun getMoviesData(
        @Query("s") tag: String,
        @Query("apikey") apiKey: String = "f7dc1c21"
    ): Response<MovieResponse>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") tag: String,
        @Query("apikey") apiKey: String = "f7dc1c21"
    ): Response<MovieDetails>
}