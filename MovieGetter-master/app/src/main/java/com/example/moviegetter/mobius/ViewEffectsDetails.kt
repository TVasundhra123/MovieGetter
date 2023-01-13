package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

sealed class ViewEffectsDetails

data class RenderMovieDetailSuccessEffect(
    val detailResponse: MovieDetails
): ViewEffectsDetails()





