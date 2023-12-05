package com.project.freshtomatoes.ui.pages.AuthLogin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.AuthState
import com.project.freshtomatoes.ui.Router
import com.project.freshtomatoes.ui.components.AuthStatus
import com.project.freshtomatoes.ui.components.PasswordField
import com.project.freshtomatoes.ui.factories.AuthLoginViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLoginScreen(viewmodel: AuthLoginViewModel = viewModel(factory = AuthLoginViewModelFactory())) {
    val navController = LocalNavController.current

    val email = viewmodel.email.collectAsState()
    val password = viewmodel.password.collectAsState()
    val errorMessage = viewmodel.errorMessage.collectAsState()
    val loginState = viewmodel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Log In", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))

        TextField(
            value = email.value,
            onValueChange = { viewmodel.setEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.padding(20.dp)
        )
        PasswordField("Password", password, { viewmodel.setPassword(it) })

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { navController.navigate(Router.SignUp.route) }) {
                Text("Sign up")
            }
            Button(onClick = { viewmodel.signIn() }) {
                Text("Log In")
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("Create an Account     ")
            Text("                      ")
        }

        AuthStatus(loginState, errorMessage, "Processing login...")
    }

    if(loginState.value == AuthState.Success) {
        navController.navigate(Router.Home.route)
    }
}
