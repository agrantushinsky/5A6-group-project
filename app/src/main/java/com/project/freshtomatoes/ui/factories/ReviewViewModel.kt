package com.project.freshtomatoes.ui.factories

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

//Coded by Jose
/**
 * ReviewViewModel for the viewmodels page.
 */
class ReviewViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    // StateFlow for movie.
    val movie = MutableStateFlow<Movie?>(null)

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
            movie.value = response
        }
    }
}
