package com.example.moviegetter.mobius

import android.util.Log
import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies

data class ModelTab(
    val status: Int,
    val searchTag: String,
    val data: MutableMap<String,List<Movies>?> = mutableMapOf(),
    val errorResponse: Throwable?,
) {
    fun getUpdatedSearchTag(searchTag: String): ModelTab {
        Log.d("TAG", "Model Got the searchTag $searchTag")
        return copy(status = -1, searchTag=searchTag, errorResponse = null)
    }

    fun responseSuccess(newData: List<Movies>?, tag:String): ModelTab {
        val newDataMap = data
        newDataMap[tag] = newData
        return copy(status = 1, searchTag= tag, data = newDataMap, errorResponse = null)
    }


    companion object {
        fun initialModel() = ModelTab(
            status = -1,
            searchTag = "",
            errorResponse = null
        )
    }
}
