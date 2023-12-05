package com.project.freshtomatoes.ui.pages.AuthSignUp

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
 * AuthSignUpViewModel is the viewmodel for the AuthSignUp page.
 * Getters & Setters for: email, password, confirmPassword.
 * Getters for: errorMessage, signupState.
 * Signup function to facilitate login process.
 * signupState:
 *  - None -> Nothing has happened yet.
 *  - Processing -> Firestore is processing the request.
 *  - Success -> Signup request was successful.
 *  - Failure -> Signup request was unsuccessful.
 */
class AuthSignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun currentUser(): StateFlow<User?> {
        return authRepository.currentUser()
    }

    // private UI state (MutableStateFlow)
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _confirmPassword = MutableStateFlow("")
    private val _errorMessage = MutableStateFlow("")
    private val _signupState = MutableStateFlow(AuthState.None)

    // public getters for the state (StateFlow)
    val email = _email.asStateFlow()
    val password = _password.asStateFlow()
    val confirmPassword = _confirmPassword.asStateFlow()
    val errorMessage = _errorMessage.asStateFlow()
    val signupState = _signupState.asStateFlow()

    // public setters for the state
    fun setEmail(newEmail: String) { _email.update { newEmail } }
    fun setPassword(newPassword: String) { _password.update { newPassword } }
    fun setConfirmPassword(newConfirmPassword: String) { _confirmPassword.update { newConfirmPassword } }

    /**
     * Attempts to sign up the user using the current state of email & password.
     */
    fun signUp() {
        // Empty email
        if(_email.value.isEmpty()) {
            setError("Email field is empty")
            return
        }

        // Empty password
        if(_password.value.isEmpty() && _confirmPassword.value.isEmpty()) {
            setError("Password field is empty")
            return
        }

        // Password not matching:
        if(_password.value != _confirmPassword.value) {
            setError("Passwords do not match")
            return
        }

        // Set the signup state while Firebase attempts
        // to process the request.
        _signupState.update { AuthState.Processing }

        viewModelScope.launch {
            try {
                val result = authRepository.signUp(email.value, password.value)
                if(!result) {
                    setError("Invalid email")
                } else {
                    _errorMessage.update { "" }
                    _signupState.update { AuthState.Success }
                }
            } catch (e: Exception) {
                println("${e.message}")
            }
        }
    }

    private fun setError(error: String) {
        _errorMessage.update { error }
        _signupState.update { AuthState.Failure }
    }
}
