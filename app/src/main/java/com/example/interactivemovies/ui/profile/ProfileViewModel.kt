package com.example.interactivemovies.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Event
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    //private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _model = MutableLiveData<Event<ProfileModel>>()
    val model: LiveData<Event<ProfileModel>>
        get() = _model


    sealed class ProfileModel{
        //TODO
    }
}