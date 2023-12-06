package com.project.freshtomatoes.ui.layout

import androidx.lifecycle.ViewModel
import com.project.freshtomatoes.ui.firebase.AuthRepository
import com.project.freshtomatoes.ui.firebase.User
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    // Return a StateFlow so that the composable can always update
    // based when the value changes
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }
}
