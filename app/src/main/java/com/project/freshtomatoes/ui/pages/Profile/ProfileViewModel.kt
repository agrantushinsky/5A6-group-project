package com.project.freshtomatoes.ui.pages.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.MovieReviewMatcher
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date

class ProfileViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    var yourReviews = MutableStateFlow<List<MovieReview>>(emptyList())

    fun updateYourReviews() {
        if(!shouldShowReviews()) {
            return
        }

        val uid = FreshTomatoes.appModule.authRepository.currentUser().value!!.uid
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.getReviewsByUID(uid).collect {
                val moviereviews = MovieReviewMatcher(it, _requester)
                val dateComparator = Comparator {
                    mr1: MovieReview, mr2: MovieReview -> if(Date(mr1.review.reviewDate).time < Date(mr2.review.reviewDate).time) 1 else -1
                }

                moviereviews.sortedWith(dateComparator)

                yourReviews.value = moviereviews
            }
        }
    }

    fun shouldShowReviews(): Boolean {
        val currentUser = FreshTomatoes.appModule.authRepository.currentUser().value
        return currentUser != null
    }
}
