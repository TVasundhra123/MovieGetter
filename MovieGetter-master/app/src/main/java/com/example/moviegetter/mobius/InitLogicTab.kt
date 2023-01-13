package com.example.moviegetter.mobius

import com.spotify.mobius.First
import com.spotify.mobius.Init

object InitLogicTab: Init<ModelTab, EffectTab> {
    override fun init(model: ModelTab): First<ModelTab, EffectTab> {
        return First.first(model)
    }
}