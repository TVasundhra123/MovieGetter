package com.example.moviegetter.mobius

import android.util.Log
import com.spotify.mobius.Next
import com.spotify.mobius.Update
import com.spotify.mobius.Effects

class LogicDetails: Update<ModelDetails, EventsDetails, EffectDetails> {
    override fun update(model: ModelDetails, event: EventsDetails): Next<ModelDetails, EffectDetails> {
        return when(event) {
            is MovieDetailApiCallEvent -> {
            //    Log.d("TAG", "In SearchClickEvent and also got the search tag ${event.searchTag}")
                val updatedModel = model.getUpdatedMovieTag(event.movieTag)
                Next.next(
                    updatedModel,
                    Effects.effects(
                        MovieDetailApiCallEffect(updatedModel.detailTag)
                    )
                )
            }

            is MovieDetailsApiSuccessEvent -> {
                val updatedModel = model.responseDetailsSuccess( event.detailResponse )
                Next.next(
                    updatedModel,
                    Effects.effects(
                        updatedModel.detailMoviesResponse?.let {
                           MovieDetailApiSuccessEffect(
                               it
                           )
                        }
                    )
                )
            }



        }

    }
}