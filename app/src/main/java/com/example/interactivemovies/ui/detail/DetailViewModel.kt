package com.example.interactivemovies.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Event
import com.example.domain.Movie
import com.example.usecases.MoviesUseCases
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

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    var movieName= ""

    sealed class DetailModel {
        data class ShowMovieDetail(val movie: Movie) : DetailModel()
        data class ShowError(val error: String) : DetailModel()
    }

    init {
        showProgress(true)
        viewModelScope.launch {
            val movie = moviesUseCases.getMovieByID(movieID)
            movieName = movie.name
            _model.value = Event(DetailModel.ShowMovieDetail(movie))
        }
    }

    fun showProgress(show: Boolean) {
        _showProgress.value = show
    }
}