package com.project.freshtomatoes.ui.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.pages.AuthSignUp.AuthSignUpViewModel

//Coded by Jose
// Factory for AuthSignUpModelFactory, passing authRepository.
class AuthSignUpModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthSignUpViewModel(FreshTomatoes.appModule.authRepository) as T
    }
}
