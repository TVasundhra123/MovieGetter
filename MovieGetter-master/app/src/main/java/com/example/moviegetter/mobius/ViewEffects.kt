package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

sealed class ViewEffects

data class RenderSuccessEffect(
    val movieList: List<Movies>?
) : ViewEffects()

data class RenderErrorEffect(
    val throwable: Throwable?
): ViewEffects()

