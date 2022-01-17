package com.example.interactivemovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Event
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    //private val userUseCases: UserUseCases,
) : ViewModel() {

    private val _model = MutableLiveData<Event<DetailModel>>()
    val model: LiveData<Event<DetailModel>>
        get() = _model


    sealed class DetailModel{
        //TODO
    }
}