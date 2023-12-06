package com.project.freshtomatoes.ui.pages.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.MovieReviewMatcher
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.firebase.AuthRepository
import com.project.freshtomatoes.ui.firebase.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class ProfileViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _requester = TmdbRequest()

    var yourReviews = MutableStateFlow<List<MovieReview>>(emptyList())

    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    fun updateYourReviews() {
        if (!shouldShowReviews()) {
            return
        }

        val uid = FreshTomatoes.appModule.authRepository.currentUser().value!!.uid
        viewModelScope.launch(Dispatchers.IO) {
            FreshTomatoes.appModule.reviewRepository.getReviewsByUID(uid).collect {
                val moviereviews = MovieReviewMatcher(it, _requester)
                val dateComparator = Comparator {
                        mr1: MovieReview, mr2: MovieReview ->
                    if (Date(mr1.review.reviewDate).time < Date(mr2.review.reviewDate).time) 1 else -1
                }

                moviereviews.sortedWith(dateComparator)
                moviereviews.take(5)

                yourReviews.value = moviereviews
            }
        }
    }

    fun shouldShowReviews(): Boolean {
        val currentUser = FreshTomatoes.appModule.authRepository.currentUser().value
        return currentUser != null
    }

    fun signOut() {
        authRepository.signOut()
    }

    fun delete() {
        viewModelScope.launch {
            authRepository.delete()
        }
    }
}
