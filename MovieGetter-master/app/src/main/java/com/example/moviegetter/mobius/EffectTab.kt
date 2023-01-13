package com.example.moviegetter.mobius

import com.example.moviegetter.recyclerview.Movies

sealed class EffectTab

data class TabEffect(val searchTag: String): EffectTab()

data class TabSuccessEffect(val list: List<Movies>?, val tag: String): EffectTab()
