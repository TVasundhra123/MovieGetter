package com.example.moviegetter.mobius

import com.spotify.mobius.First
import com.spotify.mobius.Init

object InitLogicDetails: Init<ModelDetails,EffectDetails> {
    override fun init(model: ModelDetails): First<ModelDetails, EffectDetails> {
        return First.first(model)
    }
}