package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.pages.MovieReviews.MovieReviewsViewModel
// Coded by Aidan
// Factory for MovieReviewsViewModelFactory
class MovieReviewsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieReviewsViewModel() as T
    }
}
