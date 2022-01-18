package com.example.interactivemovies.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.domain.Event
import com.example.interactivemovies.R
import com.example.interactivemovies.databinding.ProfileFragmentBinding
import com.example.interactivemovies.setFragmentBars
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
                    binding.tvCard.text = getString(R.string.card, model.user.cardNumber)

                }
                is ProfileModel.ShowUserTransactions -> {
                    with(binding){
                        name1.text = model.transactions.name1
                        balance1.text = model.transactions.balance1
                        name2.text = model.transactions.name2
                        balance2.text = model.transactions.balance2
                        name3.text = getString(R.string.current_level, model.transactions.level)
                        balance3.text = getString(R.string.next_level, model.transactions.nextLevel)
                        message.text = model.transactions.message
                    }
                }
            }
        }
    }
}