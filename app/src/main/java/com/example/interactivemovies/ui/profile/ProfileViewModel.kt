package com.example.interactivemovies.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.User
import com.example.domain.UserTransactions
import com.example.interactivemovies.ui.profile.ProfileViewModel.ProfileModel.*
import com.example.usecases.UserUseCases
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

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    var cardNo = ""

    sealed class ProfileModel {
        data class ShowUserProfile(val user: User) : ProfileModel()
        data class ShowUserTransactions(val transactions: UserTransactions) : ProfileModel()
        data class ShowError(val error: String) : ProfileModel()
    }

    init {
        showProgress(true)
        viewModelScope.launch {
            _model.value = when (val response = userUseCases.getUserProfile()) {
                is Either.Left -> Event(ShowError(response.l))
                is Either.Right -> Event(ShowUserProfile(response.r))
            }
        }
    }

    fun showProgress(show: Boolean) {
        _showProgress.value = show
    }

    fun getUserTransactions() {
        showProgress(true)
        if(cardNo.length == 16){
            viewModelScope.launch {
                _model.value = when (val response = userUseCases.getUserTransactions(cardNo)) {
                    is Either.Left -> Event(ShowError(response.l))
                    is Either.Right -> Event(ShowUserTransactions(response.r))
                }
            }
        } else _model.value = Event(ShowError("Error con los datos de la tarjeta"))

    }
}