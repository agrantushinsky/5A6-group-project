package com.project.freshtomatoes.ui.pages.EmailPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.ui.firebase.AuthRepository
import com.project.freshtomatoes.ui.firebase.User
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthSignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signUp(email, password)
        }
        /*
        try {
            val result = authRepository.signUp(email, password)
            return result
        } catch (e: Exception) {
            println("${e.message}")
            return false
        }*/
    }
}