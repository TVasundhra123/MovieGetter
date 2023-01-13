package com.example.moviegetter.mobius

class ViewRendererDetails( private val viewCallBackForDetails: ViewCallbackForDetails) {
    fun updateView(viewEffects: ViewEffectsDetails) {
        when(viewEffects) {

            is RenderMovieDetailSuccessEffect -> viewCallBackForDetails.showDetailsOfMovieOnClick(viewEffects.detailResponse)
        }
    }
}