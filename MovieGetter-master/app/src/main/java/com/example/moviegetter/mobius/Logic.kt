package com.example.moviegetter.mobius

import android.util.Log
import com.spotify.mobius.Next
import com.spotify.mobius.Update
import com.spotify.mobius.Effects

class Logic: Update<Model, Events, Effect> {
    override fun update(model: Model, event: Events): Next<Model, Effect> {
        return when(event) {
            is SearchIconClickEvent -> {
                Log.d("TAG", "In SearchClickEvent and also got the search tag ${event.searchTag}")
                val updatedModel = model.getUpdatedSearchTag(event.searchTag)
                Next.next(
                    updatedModel,
                    Effects.effects(
                        SearchIconClickEffect(updatedModel.searchTag)
                    )
                )
            }

            is ApiSuccessEvent -> {
                val updatedModel = model.responseSuccess( event.response?.Search )
                Next.next(
                    updatedModel,
                    Effects.effects(
                        SuccessEffect(
                            updatedModel.data
                        )
                    )
                )
            }

            is ApiFailureEvent -> Next.next(
                model.responseFailure(event.throwable),
                Effects.effects(
                    FailureEffect(event.throwable)
                )
            )


        }

    }
}