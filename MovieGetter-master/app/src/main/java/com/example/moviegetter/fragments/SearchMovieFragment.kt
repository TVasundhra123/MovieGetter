package com.example.moviegetter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.spotify.mobius.android.MobiusLoopViewModel

 class SearchMovieFragment : Fragment(), ViewCallback{

    private lateinit var binding: FragmentSearchMovieBinding
    private lateinit var viewModel: MobiusLoopViewModel<Model, Events, Effect, ViewEffects>
    private lateinit var apiService: RetrofitApi
    private lateinit var madapter: MoviesAdapter
    private lateinit var searchTag: String

    private val render by lazy {
        ViewRenderer(this)
    }

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

        viewModel = (requireActivity() as MainActivity).mobiusViewModel()

        binding.searchIcon.setOnClickListener {
            searchTag = binding.searchMoviesEditTextView.text.toString()
            viewModel.dispatchEvent(SearchIconClickEvent(searchTag))
            Log.d("TAG", "Search Icon Click Event Fired $searchTag")
        }

        viewModel.viewEffects.setObserver(
            this
        ) {
            render(it)
        }
    }

    private fun render(it: ViewEffects) {
        render.updateView(it)
    }

    override fun showList(list: List<Movies>?) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            madapter = MoviesAdapter(mutableListOf(),(requireActivity() as MoviesItemClicked))
            binding.recyclerView.adapter = madapter
            madapter.setData(list)
        }
    }

     override fun showErrorToast() {
        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_LONG).show()
    }

}

interface ViewModelProvider {
    fun mobiusViewModel() : MobiusLoopViewModel<Model, Events, Effect, ViewEffects>
}




