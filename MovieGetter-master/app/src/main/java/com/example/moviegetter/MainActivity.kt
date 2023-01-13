package com.example.moviegetter


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.moviegetter.databinding.ActivityMainBinding
import com.example.moviegetter.fragments.*
import com.example.moviegetter.mobius.*
import com.example.moviegetter.network.MoviesRepository
import com.example.moviegetter.network.RetrofitApi
import com.example.moviegetter.network.RetrofitService
import com.example.moviegetter.recyclerview.Movies
import com.example.moviegetter.recyclerview.MoviesItemClicked
import com.spotify.mobius.Mobius
import com.spotify.mobius.android.MobiusLoopViewModel
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxConnectables
import io.reactivex.ObservableTransformer


class MainActivity : AppCompatActivity(), MoviesItemClicked, ViewModelProvider,  ViewModelGetter {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MobiusLoopViewModel<Model, Events, Effect, ViewEffects>
    private lateinit var viewModelDetails: MobiusLoopViewModel<ModelDetails,EventsDetails,EffectDetails,ViewEffectsDetails>
    private lateinit var apiService: RetrofitApi



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, SearchMovieFragment())
            commit()
        }

        apiService = RetrofitService.getApiService()

        viewModel = MobiusLoopViewModel.create(
            { consumer: Consumer<ViewEffects> ->
                Mobius.loop(Logic(), RxConnectables.fromTransformer(effectHandler(consumer)))
            },
            Model.initialModel(),
            {
                InitLogic.init(it)
            }
        )

        viewModelDetails = MobiusLoopViewModel.create(
            { consumer: Consumer<ViewEffectsDetails> ->
                Mobius.loop(LogicDetails(), RxConnectables.fromTransformer(effectHandlerDetails(consumer)))
            },
            ModelDetails.initialModel(),
            {
                InitLogicDetails.init(it)
            }
        )

    }

    private fun effectHandler(consumer: Consumer<ViewEffects>): ObservableTransformer<Effect, Events> =
        EffectHandler.createEffectHandler(apiService, consumer)

    private fun effectHandlerDetails(consumer: Consumer<ViewEffectsDetails>): ObservableTransformer<EffectDetails, EventsDetails> =
        EffectHandlerDetails.createEffectHandler(apiService, consumer)

    override fun mobiusViewModel(): MobiusLoopViewModel<Model, Events, Effect, ViewEffects> {
        return viewModel
    }

    override fun onMovieItemClicked(item: Movies) {
       supportFragmentManager.beginTransaction().apply {
           add(binding.fragmentContainer.id, MovieDetailsFragment.newInstance(item.imdbID))
           addToBackStack(null)
           commit()
       }
    }

    override fun findViewModel(): MobiusLoopViewModel<ModelDetails, EventsDetails, EffectDetails, ViewEffectsDetails> {
        return viewModelDetails
    }


}
