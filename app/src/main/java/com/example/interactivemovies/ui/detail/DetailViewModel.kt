package com.example.interactivemovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Event
import com.example.domain.Movie
import com.example.usecases.MoviesUseCases
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases,
    @Named("movieID") movieID: Int,
) : ViewModel() {

    private val _model = MutableLiveData<Event<DetailModel>>()
    val model: LiveData<Event<DetailModel>>
        get() = _model


    sealed class DetailModel {
        data class ShowMovieDetail(val movie: Movie): DetailModel()
        data class ShowError(val error: String): DetailModel()
    }

    init {
        Logger.d(movieID)
        viewModelScope.launch {
            _model.value =Event(DetailModel.ShowMovieDetail(moviesUseCases.getMovieByID(movieID)))
        }
    }
}