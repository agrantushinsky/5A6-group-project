package com.project.freshtomatoes.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import com.project.freshtomatoes.ui.pages.EmailPassword.AuthState

@Composable
fun AuthStatus(status: State<AuthState>, errorMessage: State<String>, processingText: String) {
    if (status.value == AuthState.Failure) {
        Text(errorMessage.value, color = Color.Red)
    } else if(status.value == AuthState.Processing) {
        Text(processingText)
    }
}