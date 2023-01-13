package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

sealed class EffectDetails

data class MovieDetailApiCallEffect(val movieTag: String): EffectDetails()

data class MovieDetailApiSuccessEffect(val detailResponse: MovieDetails): EffectDetails()