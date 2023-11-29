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

        fun postReview(review: Review) {
            viewModelScope.launch(Dispatchers.IO) {
              FreshTomatoes.appModule.reviewRepository.saveReview(
               review
            )
            }
        }
    }


