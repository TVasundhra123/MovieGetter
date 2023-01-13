package com.example.moviegetter.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviegetter.databinding.FragmentSampleBinding
import com.example.moviegetter.fragments.MovieListFragment
import com.example.moviegetter.mobius.*
import com.example.moviegetter.recyclerview.Movies
import com.spotify.mobius.android.MobiusLoopViewModel


class SampleFragment : Fragment(), ViewCallbackForTab {

    private lateinit var binding: FragmentSampleBinding
    private lateinit var viewModel: MobiusLoopViewModel<ModelTab, EventTab, EffectTab, ViewEffectsTab>
    private lateinit var searchTag: String

    private val render by lazy {
        ViewRenderTab(this)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  viewModel = (requireActivity() as MainActivity).getViewModelForTab()
        searchTag = "Sample"
        viewModel.dispatchEvent(TabEvent(searchTag))
        Log.d("VASU", "Sample fragment in onViewreated")

        viewModel.viewEffects.setObserver(
            this
        ) {
            render.updateViewForTabFragment(it)
            Log.d("VASU", "Transformer fragment called render")
        }

       */



    }

    override fun setListFragment(list: List<Movies>?, tag: String) {
        childFragmentManager.beginTransaction().apply {
            Log.d("VASU", "Sample fragment in setList")
            replace(binding.sampleFragment.id, MovieListFragment.newInstance(list))
            commit()
        }
    }

}
