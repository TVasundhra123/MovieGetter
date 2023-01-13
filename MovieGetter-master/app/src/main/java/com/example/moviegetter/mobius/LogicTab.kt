package com.example.moviegetter.mobius

import android.util.Log
import com.spotify.mobius.Next
import com.spotify.mobius.Update
import com.spotify.mobius.Effects

class LogicTab: Update<ModelTab, EventTab, EffectTab> {
    override fun update(model: ModelTab, event: EventTab): Next<ModelTab, EffectTab> {
        return when(event) {
            is TabEvent -> {
                val updatedModel = model.getUpdatedSearchTag(event.searchTag)
                Next.next(
                    updatedModel,
                    Effects.effects(
                        TabEffect(event.searchTag)
                    )
                )
            }

            is ApiSuccessEventForTab -> {
                val updatedModel = model.responseSuccess( event.response?.Search,  event.tag)
                Next.next(
                    updatedModel,
                    Effects.effects(
                        TabSuccessEffect(
                            event.response?.Search,
                            event.tag
                        )
                    )
                )
            }



        }

    }
}