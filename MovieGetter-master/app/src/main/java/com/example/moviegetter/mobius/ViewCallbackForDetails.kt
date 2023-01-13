package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

interface ViewCallbackForDetails {

    fun showDetailsOfMovieOnClick(detailResponse: MovieDetails)
}