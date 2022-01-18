package com.example.interactivemovies.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.interactivemovies.R
import com.example.interactivemovies.databinding.DetailFragmentBinding
import com.example.interactivemovies.databinding.ProfileFragmentBinding
import com.example.interactivemovies.setFragmentBars
import com.example.interactivemovies.ui.detail.DetailViewModel
import com.example.interactivemovies.ui.login.LoginFragmentDirections
import com.example.interactivemovies.ui.login.LoginViewModel
import com.example.interactivemovies.ui.profile.ProfileViewModel.ProfileModel
import com.example.interactivemovies.ui.profile.ProfileViewModel.ProfileModel.ShowError
import com.example.interactivemovies.ui.profile.ProfileViewModel.ProfileModel.ShowUserProfile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setFragmentBars(
            activity as AppCompatActivity,
            AppBarVisible = true,
            bottomBarVisible = true
        )
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.viewModel = viewModel

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        viewModel.showProgress.observe(viewLifecycleOwner, Observer(::showProgress))

        return binding.root
    }

    private fun showProgress(show: Boolean) {
        binding.progressCircular.visibility = if(show) {
            VISIBLE
        } else {
            binding.profileLayout.visibility = VISIBLE
            GONE
        }
    }

    private fun changedUI(event: Event<ProfileModel>) {
        viewModel.showProgress(false)
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is ShowError -> Toast.makeText(requireContext(), model.error, Toast.LENGTH_LONG).show()
                is ShowUserProfile -> {
                    binding.tvName.text = getString(R.string.welcome, "${model.user.firstName} ${model.user.lastName}")
                    binding.tvEmail.text = getString(R.string.email, model.user.email)
                    binding.tvCard.text = getString(R.string.card_no, model.user.cardNumber)

                }
            }
        }
    }

}