package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.MovieResponse
import com.example.moviegetter.recyclerview.Movies

sealed class Events

data class SearchIconClickEvent(val searchTag: String): Events()

data class ApiSuccessEvent( val response: MovieResponse?): Events()

data class ApiFailureEvent(val throwable: Throwable): Events()