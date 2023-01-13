package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.Movies

sealed class ViewEffectsTab

data class RenderTabSuccessEffect(
    val movieList: List<Movies>?,
    val tag: String
): ViewEffectsTab()