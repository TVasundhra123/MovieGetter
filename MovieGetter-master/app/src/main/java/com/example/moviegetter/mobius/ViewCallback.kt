package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

interface ViewCallback {
    fun showErrorToast()
    fun showList(list: List<Movies>?)
}