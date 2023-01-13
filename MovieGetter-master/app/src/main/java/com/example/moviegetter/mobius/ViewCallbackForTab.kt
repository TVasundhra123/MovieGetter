package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.Movies

interface ViewCallbackForTab {
    fun setListFragment(list: List<Movies>?, tag: String)
}