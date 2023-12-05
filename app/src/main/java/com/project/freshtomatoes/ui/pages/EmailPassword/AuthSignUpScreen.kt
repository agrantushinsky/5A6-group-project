package com.project.freshtomatoes.ui.pages.EmailPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.Router
import com.project.freshtomatoes.ui.components.PasswordField
import com.project.freshtomatoes.ui.factories.AuthSignUpModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthSignUpScreen(viewmodel: AuthSignUpViewModel = viewModel(factory = (AuthSignUpModelFactory()))) {
    val navController = LocalNavController.current

    var email by rememberSaveable { mutableStateOf("") }

    var password = rememberSaveable { mutableStateOf("") }
    var confirmPassword = rememberSaveable { mutableStateOf("") }
    var passwordVisibility = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isValid by rememberSaveable {
            mutableStateOf(true)
        }
        var errorMessage by rememberSaveable {
            mutableStateOf("")
        }

        Text("Sign Up", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.padding(20.dp))
        PasswordField("Password", password, passwordVisibility)
        PasswordField("Confirm Password", confirmPassword, passwordVisibility)

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                if (email == "" || password.value == "" || confirmPassword.value == "") {
                    isValid = false
                    errorMessage = "One or more fields are empty."
                } else if (password.value != confirmPassword.value) {
                    isValid = false
                    errorMessage = "The passwords do not match."
                } else {
                    viewmodel.signUp(email, password.value)

                    navController.navigate(Router.Home.route)
                }
            }) {
                Text("Sign Up")
            }
            Button(onClick = {
                navController.navigate(Router.Account.route)
            }) {
                Text("Log In")
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("                           ")
            Text("Already have an account?")
        }
        if (!isValid) {
            Text(errorMessage, color = Color.Red)
        }
    }
}
