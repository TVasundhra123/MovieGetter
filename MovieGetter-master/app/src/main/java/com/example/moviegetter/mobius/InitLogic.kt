package com.example.moviegetter.mobius

import com.spotify.mobius.First
import com.spotify.mobius.Init

object InitLogic: Init<Model,Effect> {
    override fun init(model: Model): First<Model, Effect> {
        return First.first(model)
    }
}