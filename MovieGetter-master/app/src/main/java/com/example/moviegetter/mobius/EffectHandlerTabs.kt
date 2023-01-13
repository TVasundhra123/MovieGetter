package com.example.moviegetter.mobius

import android.app.usage.EventStats
import android.util.Log
import com.example.moviegetter.network.RetrofitApi
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object EffectHandlerTabs {
    fun createEffectHandler(
        apiService: RetrofitApi,
        viewEffectConsumer: Consumer<ViewEffectsTab>
    ): ObservableTransformer<EffectTab, EventTab> {
        return RxMobius.subtypeEffectHandler<EffectTab, EventTab>()
            .addTransformer(
                TabEffect::class.java,
                callApiForTab(apiService)
            )

            .addConsumer(
                TabSuccessEffect::class.java
            ) { effect ->
                viewEffectConsumer.accept(
                    RenderTabSuccessEffect(
                        effect.list,
                        effect.tag
                    )
                )
            }
            .build()
    }

    private fun callApiForTab(
        apiService: RetrofitApi
    ): ObservableTransformer<TabEffect, EventTab> {
        return ObservableTransformer { observable ->
            observable.flatMap { effect ->
                ObservableSource {
                    GlobalScope.launch(Dispatchers.IO) {
                        val movieResponse = apiService.getMoviesData(
                            effect.searchTag
                        )
                        if (movieResponse.isSuccessful) {
                            withContext(Dispatchers.Main) {
                                it.onNext(ApiSuccessEventForTab(movieResponse.body(), effect.searchTag) )
                                Log.d("Vasundhra", "API Called with searchTag ${effect.searchTag}")
                            }
                        }
                    }
                }
            }
        }
    }



}
