package com.example.interactivemovies.ui.listings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.Event
import com.example.interactivemovies.R
import com.example.interactivemovies.databinding.ListingsFragmentBinding
import com.example.interactivemovies.ui.listings.ListingsViewModel.ListingsModel
import com.example.interactivemovies.ui.listings.ListingsViewModel.ListingsModel.ShowError
import com.example.interactivemovies.ui.listings.ListingsViewModel.ListingsModel.ShowListings
import com.orhanobut.logger.Logger
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

        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.findMovies()

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))
        viewModel.showProgress.observe(viewLifecycleOwner, Observer(::showProgress))

        return binding.root
    }

    private fun showProgress(show: Boolean) {
        binding.progressCircular.visibility = if (show) VISIBLE else GONE
    }

    private fun changedUI(event: Event<ListingsModel>) {
        showProgress(false)
        event.getContentIfNotHandled()?.let { model ->
            Logger.d(model)
            when (model) {
                is ShowError -> Toast.makeText(requireContext(), model.error, Toast.LENGTH_LONG)
                    .show()
                is ShowListings -> {
                    binding.rvMovies.adapter =
                        ListingsAdapter(model.listing, ListingsListener { movie ->
                            this.findNavController()
                                .navigate(
                                    ListingsFragmentDirections.actionListingsFragmentToDetailFragment(
                                        movie.id
                                    )
                                )
                        })
                }
            }
        }
    }
}