package com.example.interactivemovies.ui.listings

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.Event
import com.example.interactivemovies.R
import com.example.interactivemovies.databinding.ListingsFragmentBinding
import com.example.interactivemovies.ui.listings.ListingsViewModel.ListingsModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListingsFragment : Fragment() {
    private val viewModel: ListingsViewModel by viewModels()
    private lateinit var binding: ListingsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.listings_fragment, container, false)
        binding.viewModel = viewModel

        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(),3)

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        return binding.root
    }

    private fun changedUI(event: Event<ListingsModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is ListingsModel.ShowError -> TODO()
                is ListingsModel.ShowListings -> {
                    binding.rvMovies.adapter = ListingsAdapter(model.listing, ListingsListener { movie ->
                        this.findNavController()
                            .navigate(ListingsFragmentDirections.actionListingsFragmentToDetailFragment(
                                movie.id
                            ))
                    })
                }
            }
        }
    }
}