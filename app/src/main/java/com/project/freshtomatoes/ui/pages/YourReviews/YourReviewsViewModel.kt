package com.project.freshtomatoes.ui.pages.YourReviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.MovieReviewMatcher
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class YourReviewsViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    var yourReviews = MutableStateFlow<List<MovieReview>>(emptyList())

    fun updateYourReviews(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.getReviewsByUID(uid).collect {
                yourReviews.value = MovieReviewMatcher(it, _requester)
            }
        }
    }
}
