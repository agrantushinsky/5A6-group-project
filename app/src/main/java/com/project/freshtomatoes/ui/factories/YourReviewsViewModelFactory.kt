package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.pages.YourReviews.YourReviewsViewModel

//Coded by Nitpreet
// Factory for YourReviewsViewModelFactory
class YourReviewsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return YourReviewsViewModel() as T
    }
}
