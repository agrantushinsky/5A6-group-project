package com.project.freshtomatoes.ui.pages.MovieReviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MovieReviewsViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    var movieReviews = MutableStateFlow<List<Review>>(emptyList())

    fun updateMovieReviews(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.getReviewsByMovieID(movieId).collect {
                movieReviews.value = it
            }
        }
    }
}
