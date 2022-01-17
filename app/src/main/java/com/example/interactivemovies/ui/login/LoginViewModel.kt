package com.example.interactivemovies.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Event
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    //private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _model = MutableLiveData<Event<LoginModel>>()
    val model: LiveData<Event<LoginModel>>
        get() = _model


    sealed class LoginModel{
        data class GoToProfile(val message: String): LoginModel()
        data class ShowError(val message: String): LoginModel()
    }

    fun login(){
        _model.value = Event(LoginModel.GoToProfile("Success"))
    }
}