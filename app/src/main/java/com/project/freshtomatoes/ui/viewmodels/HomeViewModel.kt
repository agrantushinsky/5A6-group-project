package com.project.freshtomatoes.ui.viewmodels

import androidx.compose.runtime.MutableState
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
    var movieList: List<Movie> = emptyList(),
    val populateMovies: suspend (MovieDisplay) -> Unit,
)

class HomeViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    private val popularMovies = mutableStateOf(MovieDisplay("Popular Movies") {
        val response = _requester.popularMovies()
        it.movieList = response.results
    })

    private val newMovies = mutableStateOf(MovieDisplay("New Movies") {
        val response = _requester.nowPlayingMovies()
        it.movieList = response.results
    })

    init {
        viewModelScope.launch(Dispatchers.IO) {
            popularMovies.value.populateMovies(popularMovies.value)
            newMovies.value.populateMovies(newMovies.value)
        }
    }

    fun getMovieDisplay(): List<MovieDisplay> {
        return listOf(popularMovies.value, newMovies.value)
    }
}
