package com.example.moviegetter.mobius

import android.util.Log
import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

data class Model(
    val status: Int,
    val searchTag: String,
    val data: List<Movies>? = null,
    val errorResponse: Throwable?,
    val detailTag: String,
    val detailMoviesResponse: MovieDetails? = null
) {
    fun getUpdatedSearchTag(searchTag: String): Model {
        Log.d("TAG", "Model Got the searchTag $searchTag")
        return copy(status = -1, searchTag=searchTag, data =null, errorResponse = null)
    }

    fun responseSuccess(newData: List<Movies>?): Model {
        return copy(status = 1, searchTag=searchTag, data = newData, errorResponse = null)
    }

    fun responseFailure(throwable: Throwable): Model = copy(
        status = -1,
        searchTag="",
        data = null,
        errorResponse = errorResponse
    )

    fun getUpdatedMovieTag(detailTag: String): Model {
        Log.d("TAG", "Model Got the searchTag $searchTag")
        return copy(status = 1, searchTag=searchTag, data =data, errorResponse = null, detailTag=detailTag, detailMoviesResponse=null)
    }

    fun responseDetailsSuccess(newDetails: MovieDetails): Model {
        return copy(status = 1, searchTag=searchTag, data = data, errorResponse = null, detailTag=detailTag, detailMoviesResponse=newDetails)
    }

    companion object {
        fun initialModel() = Model(-1, "", null, null,"", null)
    }
}
