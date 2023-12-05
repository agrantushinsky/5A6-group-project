package com.project.freshtomatoes.ui.pages.EmailPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.project.freshtomatoes.ui.factories.AuthLoginViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLoginScreen(viewmodel: AuthLoginViewModel = viewModel(factory = AuthLoginViewModelFactory())) {
    val navController = LocalNavController.current
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password = rememberSaveable {
        mutableStateOf("")
    }

    var signedInSuccess = rememberSaveable {
        mutableStateOf(false)
    }
    var errorOccurred by rememberSaveable {
        mutableStateOf(false)
    }

    var loginButtonclicked by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Log In", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.padding(20.dp))
        PasswordField("Password", password)

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                navController.navigate(Router.SignUp.route)
            }) {
                Text("Sign up")
            }
            Button(onClick = {
                loginButtonclicked = true
            }) {
                Text("Log In")
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("Create an Account     ")
            Text("                      ")
        }

        if (errorOccurred) {
            Text("Invalid Credentials", color = Color.Red)
        }
    }

    if (loginButtonclicked) {
        LaunchedEffect(signedInSuccess) {
            if (email.isNotEmpty() && password.value.isNotEmpty()) {
                val result = viewmodel.signIn(email, password.value)
                signedInSuccess.value = result

                if (result) {
                    errorOccurred = false
                    navController.navigate(Router.Home.route)
                } else {
                    errorOccurred = true
                }
            }
        }
    }
}
