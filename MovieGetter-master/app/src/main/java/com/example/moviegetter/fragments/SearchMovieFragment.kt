package com.example.moviegetter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviegetter.MainActivity
import com.example.moviegetter.databinding.FragmentSearchMovieBinding
import com.example.moviegetter.mobius.*
import com.example.moviegetter.network.RetrofitApi
import com.example.moviegetter.network.RetrofitService
import com.example.moviegetter.recyclerview.Movies
import com.example.moviegetter.recyclerview.MoviesAdapter
import com.example.moviegetter.recyclerview.MoviesItemClicked
import com.example.moviegetter.viewpager.MoviePagerAdapter
import com.spotify.mobius.android.MobiusLoopViewModel
import com.google.android.material.tabs.TabLayoutMediator

 class SearchMovieFragment : Fragment() {

    private lateinit var binding: FragmentSearchMovieBinding
    private lateinit var viewModel: MobiusLoopViewModel<Model, Events, Effect, ViewEffects>
    private lateinit var apiService: RetrofitApi
    private lateinit var madapter: MoviesAdapter
    private lateinit var searchTag: String
    private lateinit var mAdapter: MoviePagerAdapter


    private val tabList = mutableListOf(
        "Avengers",
        //"Transformers",
       // "Barbie",
        "Sample",
        "Luck"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiService = RetrofitService.getApiService()

        Log.d("TAG", requireActivity().toString())

        setPager()

        viewModel = (requireActivity() as MainActivity).mobiusViewModel()

        binding.searchIcon.setOnClickListener {
            searchTag = binding.searchMoviesEditTextView.text.toString()
            viewModel.dispatchEvent(SearchIconClickEvent(searchTag))
            Log.d("TAG", "Search Icon Click Event Fired $searchTag")
        }

    }

     private fun setPager() {
         mAdapter = MoviePagerAdapter(this)
         binding.viewPager.adapter = mAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabList[position]
        }.attach()
     }
}


interface ViewModelProvider {
    fun mobiusViewModel() : MobiusLoopViewModel<Model, Events, Effect, ViewEffects>
}




