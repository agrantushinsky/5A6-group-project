package com.project.freshtomatoes.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.ui.firebase.AuthRepository
import com.project.freshtomatoes.ui.firebase.User
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.sign

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    // Return a StateFlow so that the composable can always update
    // based when the value changes
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            authRepository.signUp(email, password)
        }
    }

    fun signIn(email: String, password: String) : Boolean
    {
        var signedIn = true;
        viewModelScope.launch {
            signedIn = authRepository.signIn(email, password)
        }
        return signedIn;
    }

    fun signOut() {
        authRepository.signOut()
    }

    fun delete() {
        viewModelScope.launch {
            authRepository.delete()
        }
    }
}
