package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.components.ShowReviewsViewModel
//Coded by Jose
// Factory for ShowReviewsViewModelFactory
class ShowReviewsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShowReviewsViewModel() as T
    }
}
