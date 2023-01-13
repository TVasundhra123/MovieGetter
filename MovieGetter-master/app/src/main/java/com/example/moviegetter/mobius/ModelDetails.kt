package com.example.moviegetter.mobius

import android.util.Log
import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

data class ModelDetails(
    val detailTag: String,
    val detailMoviesResponse: MovieDetails? = null
) {

    fun getUpdatedMovieTag(detailTag: String): ModelDetails {
       // Log.d("TAG", "Model Got the searchTag $searchTag")
        return copy(detailTag=detailTag, detailMoviesResponse=null)
    }

    fun responseDetailsSuccess(newDetails: MovieDetails?): ModelDetails {
        return copy(detailTag = detailTag, detailMoviesResponse = newDetails)
    }

    companion object {
        fun initialModel() = ModelDetails( "", null)
    }
}
