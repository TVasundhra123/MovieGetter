package com.example.moviegetter


import android.os.Bundle
import android.util.Log
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
import com.example.moviegetter.viewpager.ViewModelTab
import com.spotify.mobius.Mobius
import com.spotify.mobius.android.MobiusLoopViewModel
import com.spotify.mobius.functions.Consumer
import com.spotify.mobius.rx2.RxConnectables
import io.reactivex.ObservableTransformer


class MainActivity : AppCompatActivity(), MoviesItemClicked, ViewModelProvider,  ViewModelGetter, ViewCallback, ViewModelTab {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MobiusLoopViewModel<Model, Events, Effect, ViewEffects>
    private lateinit var viewModelDetails: MobiusLoopViewModel<ModelDetails,EventsDetails,EffectDetails,ViewEffectsDetails>
    private lateinit var viewModelTab: MobiusLoopViewModel<ModelTab, EventTab, EffectTab, ViewEffectsTab>
    private lateinit var apiService: RetrofitApi
    private val render by lazy {
        ViewRenderer(this)
    }



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

        viewModel.viewEffects.setObserver(
            this
        ) {
            Log.d("VASUNDHRA", "got the effect")
            render(it)
        }

        viewModelDetails = MobiusLoopViewModel.create(
            { consumer: Consumer<ViewEffectsDetails> ->
                Mobius.loop(LogicDetails(), RxConnectables.fromTransformer(effectHandlerDetails(consumer)))
            },
            ModelDetails.initialModel(),
            {
                InitLogicDetails.init(it)
            }
        )

        viewModelTab = MobiusLoopViewModel.create(
            { consumer: Consumer<ViewEffectsTab> ->
                Mobius.loop(LogicTab(), RxConnectables.fromTransformer(effectHandlerTab(consumer)))
            },
            ModelTab.initialModel(),
            {
                InitLogicTab.init(it)
            }
        )

    }

    private fun effectHandlerTab(consumer: Consumer<ViewEffectsTab>): ObservableTransformer<EffectTab, EventTab> =
        EffectHandlerTabs.createEffectHandler(apiService, consumer)


    private fun render(viewEffects: ViewEffects) {
        render.updateView(viewEffects)
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

    override fun showErrorToast() {
        Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()
    }

    override fun showList(list: List<Movies>?) {
        supportFragmentManager.beginTransaction().apply {
           replace(binding.fragmentContainer.id, MovieListFragment.newInstance(list))
           addToBackStack(null)
           commit()
       }
    }

    override fun getViewModelForTab(): MobiusLoopViewModel<ModelTab, EventTab, EffectTab, ViewEffectsTab> {
        return viewModelTab
    }


}
