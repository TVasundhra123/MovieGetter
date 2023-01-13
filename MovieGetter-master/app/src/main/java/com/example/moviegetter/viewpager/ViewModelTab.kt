package com.example.moviegetter.viewpager

import com.example.moviegetter.mobius.EffectTab
import com.example.moviegetter.mobius.EventTab
import com.example.moviegetter.mobius.ModelTab
import com.example.moviegetter.mobius.ViewEffectsTab
import com.spotify.mobius.android.MobiusLoopViewModel

interface ViewModelTab {
    fun getViewModelForTab() : MobiusLoopViewModel<ModelTab, EventTab, EffectTab, ViewEffectsTab>
}