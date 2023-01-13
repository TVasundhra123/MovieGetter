package com.example.moviegetter.mobius

class ViewRenderTab(private val viewCallbackForTab: ViewCallbackForTab) {
    fun updateViewForTabFragment(viewEffects: ViewEffectsTab) {
        when(viewEffects) {
            is RenderTabSuccessEffect -> viewCallbackForTab.setListFragment(viewEffects.movieList, viewEffects.tag)
        }
    }
}