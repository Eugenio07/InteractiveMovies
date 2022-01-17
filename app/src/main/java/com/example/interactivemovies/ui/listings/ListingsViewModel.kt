package com.example.interactivemovies.ui.listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Event
import com.example.interactivemovies.ui.login.LoginViewModel
import javax.inject.Inject

class ListingsViewModel @Inject constructor(
    //private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _model = MutableLiveData<Event<ListingsModel>>()
    val model: LiveData<Event<ListingsModel>>
        get() = _model


    sealed class ListingsModel{
        data class GoToDetail(val message: String): ListingsModel()
    }

    fun movieDetail()
    {
        _model.value = Event(ListingsModel.GoToDetail("Success"))
    }
}