package com.example.interactivemovies.ui.listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Either
import com.example.domain.Event
import com.example.domain.Movie
import com.example.interactivemovies.ui.listings.ListingsViewModel.ListingsModel.ShowError
import com.example.interactivemovies.ui.listings.ListingsViewModel.ListingsModel.ShowListings
import com.example.usecases.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingsViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases,
) : ViewModel() {

    private val _model = MutableLiveData<Event<ListingsModel>>()
    val model: LiveData<Event<ListingsModel>>
        get() = _model

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    sealed class ListingsModel{
        data class ShowListings(val listing: List<Movie>): ListingsModel()
        data class ShowError(val error: String): ListingsModel()
    }

    fun showProgress(bool: Boolean) {
        _showProgress.value = bool
    }

    fun findMovies(){
        showProgress(true)
        viewModelScope.launch {
            _model.value =when(val response = moviesUseCases.getListings()){
                is Either.Left -> Event(ShowError(response.l))
                is Either.Right -> Event(ShowListings(response.r))
            }
        }
    }

}