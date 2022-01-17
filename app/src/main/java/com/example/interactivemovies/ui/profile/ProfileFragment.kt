package com.example.interactivemovies.ui.profile

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
import com.example.interactivemovies.databinding.ProfileFragmentBinding
import com.example.interactivemovies.ui.detail.DetailViewModel
import com.example.interactivemovies.ui.login.LoginFragmentDirections
import com.example.interactivemovies.ui.login.LoginViewModel
import com.example.interactivemovies.ui.profile.ProfileViewModel.ProfileModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        binding.viewModel = viewModel

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))

        return binding.root
    }

    private fun changedUI(event: Event<ProfileModel>) {
        event.getContentIfNotHandled()?.let { model ->
            Logger.d("model: $model")
            when (model) {
                is ProfileModel.ShowError -> TODO()
                is ProfileModel.ShowUserProfile -> {
                    //val fullName = "${model.user.firstName} ${model.user.lastName}"
                    binding.tvName.text = getString(R.string.welcome, "${model.user.firstName} ${model.user.lastName}")
                    binding.tvEmail.text = getString(R.string.email, model.user.email)
                    binding.tvCard.text = getString(R.string.card_no, model.user.cardNumber)

                }
            }
        }
    }

}