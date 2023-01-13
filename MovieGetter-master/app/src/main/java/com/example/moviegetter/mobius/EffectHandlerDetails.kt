package com.example.moviegetter.mobius

import android.util.Log
import com.example.moviegetter.network.MoviesRepository
import com.example.moviegetter.network.RetrofitApi
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object EffectHandlerDetails {

    fun createEffectHandler(
        apiService: RetrofitApi,
        viewEffectConsumer: Consumer<ViewEffectsDetails>
    ): ObservableTransformer<EffectDetails, EventsDetails> {
        return RxMobius.subtypeEffectHandler<EffectDetails, EventsDetails>()

            .addTransformer(
                MovieDetailApiCallEffect::class.java,
                callApi(apiService)

            )

            .addConsumer(
                MovieDetailApiSuccessEffect::class.java
            ) { effect ->
                viewEffectConsumer.accept(
                    RenderMovieDetailSuccessEffect(
                        effect.detailResponse
                    )
                )
            }

            .build()
    }

   private fun callApi(
       apiService: RetrofitApi
   ): ObservableTransformer<MovieDetailApiCallEffect, EventsDetails> {
       return ObservableTransformer { observable ->
           observable.flatMap { effect ->
               ObservableSource {
                   GlobalScope.launch(Dispatchers.IO) {
                       val movieDetailResponse = apiService.getMovieDetails(
                           effect.movieTag
                       )
                       if (movieDetailResponse.isSuccessful) {
                           withContext(Dispatchers.Main) {
                               it.onNext(MovieDetailsApiSuccessEvent(movieDetailResponse.body()))
                             //  Log.d("TAG", "API Called with searchTag ${effect.searchTag}")
                           }
                       }
                   }
               }
           }
       }
   }


}