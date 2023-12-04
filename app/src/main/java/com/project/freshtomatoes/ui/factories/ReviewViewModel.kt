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

class ReviewViewModel : ViewModel() {
    private val _requester = TmdbRequest()
    val movie = MutableStateFlow<Movie?>(null)

    fun postReview(review: Review) {
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.saveReview(review)
        }
    }

    fun updateMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = _requester.details(id)
            movie.value = response
        }
    }
}
