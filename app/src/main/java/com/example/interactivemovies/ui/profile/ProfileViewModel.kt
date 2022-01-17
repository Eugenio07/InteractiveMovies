package com.example.interactivemovies.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Event
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
        //TODO
    }

    init {
        viewModelScope.launch {
            val user = userUseCases.getUserProfile()
            Logger.d(user)
        }
    }
}