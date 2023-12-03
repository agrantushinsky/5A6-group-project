package com.project.freshtomatoes.ui.pages.MovieDetails

import androidx.compose.runtime.mutableStateOf
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

    val reviewed = mutableStateOf(false)

    fun getIfReviewed(uid: String, movieId: Int) {
        reviewed.value = false
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.getReviewsByUID(uid).collect {
                for (review in it) {
                    if (review.movieId == movieId) {
                        reviewed.value = true
                        break
                    }
                }
            }
        }
    }

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
