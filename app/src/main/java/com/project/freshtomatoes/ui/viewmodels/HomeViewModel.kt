package com.project.freshtomatoes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class movieDisplay(
    val label: String,
    val movieList: List<Movie>?,
    val populateMovies: (movieDisplay) -> Unit
)

class HomeViewModel() : ViewModel() {
    private val _popularMovies = movieDisplay("Popular Movies")

    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun popularMovies(): StateFlow<> {

    }
}