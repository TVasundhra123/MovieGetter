package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.Movies

sealed class Effect

data class SearchIconClickEffect(val searchTag: String): Effect()

data class SuccessEffect(val list: List<Movies>?): Effect()

data class FailureEffect(val throwable: Throwable?): Effect()