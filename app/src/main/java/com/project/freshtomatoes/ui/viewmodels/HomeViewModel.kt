package com.project.freshtomatoes.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

data class MovieDisplay(
    val label: String,
    var movieList: List<Movie> = emptyList()
)

class HomeViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    private val popularMovies by mutableStateOf(MovieDisplay("Popular Movies"))

    private val newMovies by mutableStateOf(MovieDisplay("New Movies"))

    init {
        viewModelScope.launch(Dispatchers.IO) {
            popularMovies.movieList = _requester.popularMovies().results
            newMovies.movieList = _requester.nowPlayingMovies().results
        }
    }

    fun getMovieDisplay(): List<MovieDisplay> {
        return listOf(popularMovies, newMovies)
    }
}
