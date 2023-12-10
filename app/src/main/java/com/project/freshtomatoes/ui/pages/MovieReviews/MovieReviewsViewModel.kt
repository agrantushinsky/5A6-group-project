package com.project.freshtomatoes.ui.pages.MovieReviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.MovieReviewMatcher
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

// Coded by Jose
/**
 * MovieReviewsViewModel for the Movie Reviews Page
 * Contains:
 * - updateMovieReviews: Gets all the reviews for the specified movie.
 */
class MovieReviewsViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    var movieReviews = MutableStateFlow<List<MovieReview>>(emptyList())

    fun updateMovieReviews(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.getReviewsByMovieID(movieId).collect {
                movieReviews.value = MovieReviewMatcher(it, _requester)
            }
        }
    }
}
