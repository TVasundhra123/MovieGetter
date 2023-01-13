package com.example.moviegetter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviegetter.MainActivity
import com.example.moviegetter.R
import com.example.moviegetter.databinding.FragmentMovieDetailsBinding
import com.example.moviegetter.mobius.*
import com.example.moviegetter.network.RetrofitService
import com.example.moviegetter.recyclerview.MovieDetails
import com.example.moviegetter.recyclerview.Movies
import com.spotify.mobius.android.MobiusLoopViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class MovieDetailsFragment: Fragment(), ViewCallbackForDetails {

    companion object{
        private const val key = "MOVIE_TAG"
        fun newInstance(tag: String): MovieDetailsFragment {
            return MovieDetailsFragment().apply {
                val bundle = Bundle()
                bundle.putString(key,tag)
                arguments = bundle
            }
        }
    }

    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var mTag: String
    private lateinit var viewModelDetails: MobiusLoopViewModel<ModelDetails, EventsDetails, EffectDetails, ViewEffectsDetails>

     private val render by lazy {
         ViewRendererDetails(this)
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mTag = requireArguments().getString(key)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelDetails = (requireActivity() as MainActivity).findViewModel()


        viewModelDetails.viewEffects.setObserver(
            this
        ) {
            render(it)
        }

        viewModelDetails.dispatchEvent(MovieDetailApiCallEvent(mTag))

    }

     private fun render(it: ViewEffectsDetails) {
         render.updateView(it)
     }

     override fun showDetailsOfMovieOnClick(detailResponse: MovieDetails) {
         binding.titleTextView.text = detailResponse.Title
         binding.gnereTextView.text=detailResponse.Genre
         binding.plotTextView.text=detailResponse.Plot
     }


 }

interface ViewModelGetter {
    fun findViewModel() : MobiusLoopViewModel<ModelDetails, EventsDetails, EffectDetails, ViewEffectsDetails>
}