package com.project.freshtomatoes.ui.pages.EmailPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.factories.AuthViewModelFactory
import com.project.freshtomatoes.ui.viewmodels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLoginScreen(authViewModel: AuthViewModel =
                        viewModel(factory = AuthViewModelFactory())
) {
    val userState = authViewModel.currentUser().collectAsState()
    var email by remember {
        mutableStateOf("")
    }
    Column {
        if (userState.value == null) {
            Text("Not logged in")
            TextField(value = email, onValueChange = { email = it })
            Button(onClick = {
                authViewModel.signUp("$email", "Abcd1234!")
            }) {
                Text("Sign up via email")
            }
            Button(onClick = {
                authViewModel.signIn("$email", "Abcd1234!")
            }) {
                Text("Sign in via email")
            }

        } else {
            if (userState.value==null)
                Text("Please sign in")
            else
                Text("Welcome ${userState.value!!.email}")
            Button(onClick = {
                authViewModel.signOut()
            }) {
                Text("Sign out")
            }
            Button(onClick = {
                authViewModel.delete()
            }) {
                Text("Delete account")
            }
        }
    }
}
