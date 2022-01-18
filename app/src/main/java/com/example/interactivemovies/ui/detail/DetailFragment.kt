package com.example.interactivemovies.ui.detail

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun changedUI(event: Event<DetailModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is ShowError -> TODO()
                is ShowMovieDetail -> {
                    with(binding) {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                val message = getString(R.string.share_message, viewModel.movieName)
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, message)
                startActivity(Intent.createChooser(share, requireContext().getText(R.string.share)))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}