package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.MovieResponse
import com.example.moviegetter.recyclerview.Movies

sealed class EventsDetails

data class MovieDetailApiCallEvent(val movieTag: String): EventsDetails()

data class MovieDetailsApiSuccessEvent(val detailResponse: MovieDetails?): EventsDetails()