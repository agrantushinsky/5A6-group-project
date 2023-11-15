package com.project.freshtomatoes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    val movie = MutableStateFlow<Movie?>(null)
    val averageRating = MutableStateFlow<Double?>(null)

    fun updateMovie(id: Int) {
        movie.value = null
        averageRating.value = null

        viewModelScope.launch(Dispatchers.IO) {
            val response = _requester.details(id)
            movie.value = response
            FreshTomatoes.appModule.reviewRepository.getAverageRating(response.id).collect() {
                averageRating.value = it
            }
        }
    }
}
