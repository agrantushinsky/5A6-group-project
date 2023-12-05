package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.pages.Profile.ProfileViewModel
import com.project.freshtomatoes.ui.pages.YourReviews.YourReviewsViewModel

class ProfileViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(FreshTomatoes.appModule.authRepository) as T
    }
}
