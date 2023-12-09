package com.project.freshtomatoes.ui.pages.Review

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ReviewViewModel for the viewmodels page.
 */
class ReviewViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    // StateFlow for movie.
    private val _movie = MutableStateFlow<Movie?>(null)
    private val _rating = MutableStateFlow("🍅🍅🍅🍅🍅")
    private val _review = MutableStateFlow("")

    // public getters for state
    val movie = _movie.asStateFlow()
    val rating = _rating.asStateFlow()
    val review = _review.asStateFlow()

    // Setters and rating functionality functions:
    fun setReview(reviewText: String) {
        _review.update { reviewText }
    }

    fun growTomato() {
        if (rating.value.length < 10) {
            _rating.update { rating.value + "🍅" }
        }
    }

    fun throwTomato() {
        if (rating.value.isNotEmpty()) {
            _rating.update { rating.value.substring(0, rating.value.length - 2) }
        }
    }

    /**
     * Posts a review by saving the passed review to the reviewRepository.
     *
     * @param review The Review to post.
     */
    fun postReview(review: Review) {
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.saveReview(review)
        }
    }

    /**
     * Updates the movie to display based on the passed id.
     *
     * @param id The movie ID to query.
     */
    fun updateMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = _requester.details(id)
            _movie.value = response
        }
    }
}
