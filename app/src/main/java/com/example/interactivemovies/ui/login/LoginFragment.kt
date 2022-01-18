package com.example.interactivemovies.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.domain.Event
import com.example.interactivemovies.R
import com.example.interactivemovies.databinding.LoginFragmentBinding
import com.example.interactivemovies.setFragmentBars
import com.example.interactivemovies.ui.login.LoginViewModel.LoginModel
import com.example.interactivemovies.ui.login.LoginViewModel.LoginModel.GoToProfile
import com.example.interactivemovies.ui.login.LoginViewModel.LoginModel.ShowError
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setFragmentBars(
            activity as AppCompatActivity,
            AppBarVisible = false,
            bottomBarVisible = false
        )
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        binding.viewModel = viewModel

        viewModel.model.observe(viewLifecycleOwner, Observer(::changedUI))


        return binding.root
    }

    private fun changedUI(event: Event<LoginModel>) {
        event.getContentIfNotHandled()?.let { model ->
            when (model) {
                is GoToProfile -> {
                    this.findNavController()
                        .navigate(LoginFragmentDirections.actionLoginFragmentToProfileFragment())
                }
                is ShowError -> TODO()
            }
        }
    }

}