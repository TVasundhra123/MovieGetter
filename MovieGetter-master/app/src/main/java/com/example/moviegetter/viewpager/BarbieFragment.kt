package com.example.moviegetter.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviegetter.databinding.FragmentBarbieBinding
import com.example.moviegetter.fragments.MovieListFragment
import com.example.moviegetter.mobius.*
import com.example.moviegetter.recyclerview.Movies
import com.spotify.mobius.android.MobiusLoopViewModel

class BarbieFragment : Fragment(),ViewCallbackForTab {

    private lateinit var binding: FragmentBarbieBinding
    private lateinit var searchTag: String
    private lateinit var viewModel: MobiusLoopViewModel<ModelTab, EventTab, EffectTab, ViewEffectsTab>

    private val render by lazy {
        ViewRenderTab(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBarbieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* Log.d("VASU", "Barbie fragment in onViewCreated")
        viewModel = (requireActivity() as MainActivity).getViewModelForTab()
        searchTag = "Barbie"
        viewModel.dispatchEvent(TabEvent(searchTag))

        viewModel.viewEffects.setObserver(
            this
        ) {
            render.updateViewForTabFragment(it)
        }

        */


    }

    override fun setListFragment(list: List<Movies>?, tag: String) {
        childFragmentManager.beginTransaction().apply {
            Log.d("VASU", "Barbie fragment in setList")
            replace(binding.barbieFragment.id, MovieListFragment.newInstance(list))
            commit()
        }


    }


}

