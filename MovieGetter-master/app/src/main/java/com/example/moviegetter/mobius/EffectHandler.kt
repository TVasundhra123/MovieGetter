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

object EffectHandler {

    fun createEffectHandler(
        apiService: RetrofitApi,
        viewEffectConsumer: Consumer<ViewEffects>
    ): ObservableTransformer<Effect, Events> {
        return RxMobius.subtypeEffectHandler<Effect, Events>()

            .addTransformer(
                SearchIconClickEffect::class.java,
                callApi(apiService)

            )

            .addConsumer(
                SuccessEffect::class.java
            ) { effect ->
                viewEffectConsumer.accept(
                    RenderSuccessEffect(
                        effect.list
                    )
                )
            }
            .addConsumer(
                FailureEffect::class.java
            ) { effect ->
                viewEffectConsumer.accept(
                    RenderErrorEffect(
                        effect.throwable
                    )
                )
            }
            .build()
    }

   private fun callApi(
       apiService: RetrofitApi
   ): ObservableTransformer<SearchIconClickEffect, Events> {
       return ObservableTransformer { observable ->
           observable.flatMap { effect ->
               ObservableSource {
                   GlobalScope.launch(Dispatchers.IO) {
                       val movieResponse = apiService.getMoviesData(
                           effect.searchTag
                       )
                       if (movieResponse.isSuccessful) {
                           withContext(Dispatchers.Main) {
                               it.onNext(ApiSuccessEvent(movieResponse.body()))
                               Log.d("VASUNDHRA", "Search event hu me API Called with searchTag ${effect.searchTag}")
                           }
                       }
                   }
               }
           }
       }
   }

}