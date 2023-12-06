package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.pages.AuthLogin.AuthLoginViewModel

// Factory for AuthLoginViewModelFactory, passing authRepository.
class AuthLoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthLoginViewModel(FreshTomatoes.appModule.authRepository) as T
    }
}
