package com.example.interactivemovies.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.User
import com.example.interactivemovies.ui.login.LoginViewModel
import com.example.interactivemovies.ui.profile.ProfileViewModel.ProfileModel.ShowError
import com.example.interactivemovies.ui.profile.ProfileViewModel.ProfileModel.ShowUserProfile
import com.example.usecases.UserUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _model = MutableLiveData<Event<ProfileModel>>()
    val model: LiveData<Event<ProfileModel>>
        get() = _model


    sealed class ProfileModel{
        data class ShowUserProfile(val user: User): ProfileModel()
        data class ShowError(val error: String): ProfileModel()
    }

    init {
        viewModelScope.launch {
            _model.value = when(val response = userUseCases.getUserProfile()){
                is Either.Left -> Event(ShowError(response.l))
                is Either.Right -> Event(ShowUserProfile(response.r))
            }
        }
    }
}