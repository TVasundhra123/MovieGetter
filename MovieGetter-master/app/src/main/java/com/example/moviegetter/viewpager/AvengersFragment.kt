package com.example.moviegetter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviegetter.MainActivity
import com.example.moviegetter.databinding.FragmentAvengersBinding
import com.example.moviegetter.fragments.MovieListFragment
import com.example.moviegetter.mobius.*
import com.example.moviegetter.recyclerview.Movies
import com.spotify.mobius.android.MobiusLoopViewModel


class AvengersFragment : Fragment(), ViewCallbackForTab {

    private lateinit var binding: FragmentAvengersBinding
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
        binding = FragmentAvengersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchTag = requireArguments().getString("TAG") ?: ""

        childFragmentManager.beginTransaction().apply {
            replace(binding.avengerFragmentContainer.id, MovieListFragment.newInstance(emptyList()), searchTag )
            commit()
        }


        viewModel = (requireActivity() as MainActivity).getViewModelForTab()
        viewModel.dispatchEvent(TabEvent(searchTag))

        viewModel.viewEffects.setObserver(
            this
        ) {
            render.updateViewForTabFragment(it)
        }



    }

    override fun setListFragment(list: List<Movies>?, tag: String) {
        (childFragmentManager.findFragmentByTag(tag) as? MovieListFragment)?.setAdapterData(viewModel.model.data[tag])
    }

}