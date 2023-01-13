package com.example.moviegetter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviegetter.databinding.FragmentMovieListBinding
import com.example.moviegetter.recyclerview.Movies
import com.example.moviegetter.recyclerview.MoviesAdapter
import com.example.moviegetter.recyclerview.MoviesItemClicked

private const val MOVIE_LIST = "movieList"


class MovieListFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(list: List<Movies>?) = MovieListFragment().apply {
                arguments = Bundle().apply {
              //      Log.d("VASU", "Transformer fragment in newInstancae of movie;list")
                    putParcelableArrayList(MOVIE_LIST, list?.let { java.util.ArrayList(it) })
                }
            }
    }

    private val movieList = mutableListOf<Movies>()
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var mAdapter: MoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMovieListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            mAdapter = MoviesAdapter(mutableListOf(),(requireActivity() as MoviesItemClicked))
            binding.movieRecyclerView.adapter = mAdapter
           // Log.d("VASU", "Transformer fragment in called set data")
         //   mAdapter.setData(movieList)

        }
    }

    fun setAdapterData(list: List<Movies>?) {
        if (list != null) {
            movieList.addAll(list)
            mAdapter.setData(movieList)
        }

    }
}