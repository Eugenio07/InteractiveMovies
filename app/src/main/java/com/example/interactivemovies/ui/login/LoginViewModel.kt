package com.example.interactivemovies.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.Event
import com.example.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _model = MutableLiveData<Event<LoginModel>>()
    val model: LiveData<Event<LoginModel>>
        get() = _model


    sealed class LoginModel {
        data class GoToProfile(val message: String) : LoginModel()
        data class ShowError(val message: String) : LoginModel()
    }

    fun login() {
        viewModelScope.launch {
            _model.value = when (val response =
                userUseCases.loginUser("pruebas_beto_ia@yahoo.com", "Pruebas01")) {
                is Either.Left -> Event(LoginModel.ShowError(response.l))
                is Either.Right -> Event(LoginModel.GoToProfile("Success"))
            }
        }

    }
}