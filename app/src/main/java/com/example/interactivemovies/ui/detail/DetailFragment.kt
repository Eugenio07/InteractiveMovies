package com.example.interactivemovies.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.interactivemovies.R
import com.example.interactivemovies.databinding.DetailFragmentBinding
import com.example.interactivemovies.ui.detail.DetailViewModel.DetailModel
import com.example.interactivemovies.ui.detail.DetailViewModel.DetailModel.ShowError
import com.example.interactivemovies.ui.detail.DetailViewModel.DetailModel.ShowMovieDetail
import com.example.interactivemovies.ui.listings.ListingsAdapter
import com.example.interactivemovies.ui.listings.ListingsFragmentDirections
import com.example.interactivemovies.ui.listings.ListingsListener
import com.example.interactivemovies.ui.listings.ListingsViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        return binding.root
    }

    private fun changedUI(event: Event<DetailModel>) {
        event.getContentIfNotHandled()?.let { model ->
            Logger.d("model: $model")
            when (model) {
                is ShowError -> TODO()
                is ShowMovieDetail -> {
                    Logger.d(model.movie)
                    with(binding){
                        fieldTitle.text = model.movie.name
                        fieldClassification.text = model.movie.rating
                        fieldDuration.text = model.movie.length
                        fieldGenre.text = model.movie.genre
                        fieldSynopsis.text = model.movie.synopsis
                        vvTrailer.setVideoPath(model.movie.video)
                        vvTrailer.start()
                    }

                }
            }
        }
    }
}