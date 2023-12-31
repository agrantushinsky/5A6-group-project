package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.pages.Review.ReviewViewModel

// Coded by Aidan
// Factory for ReviewViewModelFactory
class ReviewViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReviewViewModel() as T
    }
}
