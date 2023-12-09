package com.project.freshtomatoes.ui.pages.GenrePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
//Coded by Jose
/**
 * GenreViewModel for movie list by genre screen.
 * Contains:
 * - Getter for movies list.
 * - updateMovies(...) to update the list of movies to display for.
 */
class GenreViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    // Getters & setters for movies StateFlow
    private val _movies = MutableStateFlow(emptyList<Movie>())
    val movies = _movies.asStateFlow()

    fun updateMovies(genreId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movies.update { _requester.getMoviesByGenre(genreId).results }
        }
    }
}
