package com.project.freshtomatoes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MovieDisplay(
    val label: String,
    var movieList: List<Movie> = emptyList(),
    val populateMovies: suspend () -> List<Movie>,
)

class HomeViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    private val _displays = MutableStateFlow(listOf(
        MovieDisplay("Popular Movies") {
            val response = _requester.popularMovies()
            return@MovieDisplay response.results
        },
        MovieDisplay("New Movies") {
            val response = _requester.nowPlayingMovies()
            return@MovieDisplay response.results
        },
    ))

    val displays = _displays.asStateFlow()

    init {
        _displays.value.forEachIndexed { index, display ->
            viewModelScope.launch(Dispatchers.IO) {
                _displays.collect {
                    _displays.value[index].movieList = display.populateMovies()
                }
            }
        }
    }
}
