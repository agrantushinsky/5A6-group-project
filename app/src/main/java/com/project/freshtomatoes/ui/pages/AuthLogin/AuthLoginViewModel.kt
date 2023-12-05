package com.project.freshtomatoes.ui.pages.AuthLogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.freshtomatoes.data.AuthState
import com.project.freshtomatoes.ui.firebase.AuthRepository
import com.project.freshtomatoes.ui.firebase.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * AuthLoginViewModel is the viewmodel for the AuthLogin page.
 * Getters & Setters for: email, password.
 * Getters for: errorMessage, loginState.
 * Login function to facilitate login process.
 * loginState:
 *  - None -> Nothing has happened yet.
 *  - Processing -> Firestore is processing the request.
 *  - Success -> Login request was successful.
 *  - Failure -> Login request was unsuccessful.
 */
class AuthLoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    // private UI state (MutableStateFlow)
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _errorMessage = MutableStateFlow("")
    private val _loginState = MutableStateFlow(AuthState.None)

    // public getters for the state (StateFlow)
    val email = _email.asStateFlow()
    val password = _password.asStateFlow()
    val errorMessage = _errorMessage.asStateFlow()
    val loginState = _loginState.asStateFlow()

    // public setters for the state
    fun setEmail(newEmail: String) { _email.update { newEmail } }
    fun setPassword(newPassword: String) { _password.update { newPassword } }

    /**
     * Attempts to sign in the user using the current state of email & password.
     */
    fun signIn() {
        // Set the login state to processing while Firebase attempts
        // to authenticate the user.
        _loginState.update { AuthState.Processing }

        viewModelScope.launch {
            try {
                val result = authRepository.signIn(email.value, password.value)
                if(!result) {
                    _errorMessage.update { "Invalid Credentials" }
                    _loginState.update { AuthState.Failure }
                } else {
                    _errorMessage.update { "" }
                    _loginState.update { AuthState.Success }
                }
            } catch (e: Exception) {
                println("${e.message}")
            }
        }
    }
}