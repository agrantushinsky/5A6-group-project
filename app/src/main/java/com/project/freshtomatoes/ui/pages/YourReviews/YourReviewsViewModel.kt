package com.project.freshtomatoes.ui.pages.YourReviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.MovieReviewMatcher
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

//Coded by Aidan
/**
 * YourReviewsViewModel for the Your Reviews Page
 *  Contains:
 *  - updateYourReviews: Gets all the reviews made by the current user
 *  - shouldShowReviews: Checks whether there is a user logged in.
 */
class YourReviewsViewModel : ViewModel() {
    private val _requester = TmdbRequest()

    var yourReviews = MutableStateFlow<List<MovieReview>>(emptyList())

    fun updateYourReviews() {
        if (!shouldShowReviews()) {
            return
        }

        val uid = FreshTomatoes.appModule.authRepository.currentUser().value!!.uid
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.getReviewsByUID(uid).collect {
                yourReviews.value = MovieReviewMatcher(it, _requester)
            }
        }
    }

    fun shouldShowReviews(): Boolean {
        val currentUser = FreshTomatoes.appModule.authRepository.currentUser().value
        return currentUser != null
    }
}
