package com.project.freshtomatoes.ui.factories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    class ReviewViewModel : ViewModel() {
        private val _requester = TmdbRequest()

      val reviewed = mutableStateOf(false)

        fun getIfReviewed(uid: String, movieId: Int) {
            reviewed.value = false
            viewModelScope.launch(Dispatchers.IO) {
                FreshTomatoes.appModule.reviewRepository.getReviewsByUID(uid).collect {
                    for(review in it) {
                        if(review.movieId == movieId) {
                            reviewed.value = true
                            break
                        }
                    }
                }
            }
        }

        fun postReview(review: Review) {
            viewModelScope.launch(Dispatchers.IO) {
              FreshTomatoes.appModule.reviewRepository.saveReview(
               review
            )
            }
        }
    }


