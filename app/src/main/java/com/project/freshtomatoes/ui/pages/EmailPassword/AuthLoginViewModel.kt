package com.project.freshtomatoes.ui.pages.EmailPassword

import androidx.lifecycle.ViewModel
import com.project.freshtomatoes.ui.firebase.AuthRepository
import com.project.freshtomatoes.ui.firebase.User
import kotlinx.coroutines.flow.StateFlow

class AuthLoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    suspend fun signIn(email: String, password: String): Boolean {
        try {
            val result = authRepository.signIn(email, password)
            return result
        } catch (e: Exception) {
            println("${e.message}")
            return false
        }
    }
}