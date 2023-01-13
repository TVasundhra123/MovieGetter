package com.example.moviegetter.mobius

class ViewRenderer(private val viewCallback: ViewCallback) {
    fun updateView(viewEffects: ViewEffects) {
        when(viewEffects) {

            is RenderSuccessEffect -> viewCallback.showList(viewEffects.movieList)

            is RenderErrorEffect -> viewCallback.showErrorToast()
        }
    }
}