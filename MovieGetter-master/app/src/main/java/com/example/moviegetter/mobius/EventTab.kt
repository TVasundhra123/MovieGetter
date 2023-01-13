package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.MovieResponse

sealed class EventTab

data class TabEvent(val searchTag: String): EventTab()

data class ApiSuccessEventForTab( val response: MovieResponse?, val tag:String): EventTab()


