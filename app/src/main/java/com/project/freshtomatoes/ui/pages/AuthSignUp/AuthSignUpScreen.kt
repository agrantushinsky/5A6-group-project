package com.project.freshtomatoes.ui.pages.AuthSignUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.project.freshtomatoes.ui.factories.AuthSignUpModelFactory
//Coded by Jose
/**
 * AuthSignup page to allow the user to sign up. Prompting for a email password a confirmation password while offering access to login.
 * All user information is being stored using firebase while offering good proper validation and user security.
 * @param viewmodel Viewmodel for page, defaulted to viewModel using AuthSignUpViewModelFactory.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthSignUpScreen(viewmodel: AuthSignUpViewModel = viewModel(factory = (AuthSignUpModelFactory()))) {
    val navController = LocalNavController.current

    val email = viewmodel.email.collectAsState()
    val password = viewmodel.password.collectAsState()
    var confirmPassword = viewmodel.confirmPassword.collectAsState()
    val errorMessage = viewmodel.errorMessage.collectAsState()
    val signupState = viewmodel.signupState.collectAsState()

    var passwordVisibility = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign Up", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
        TextField(value = email.value, onValueChange = { viewmodel.setEmail(it) }, label = { Text("Email") }, modifier = Modifier.padding(20.dp))
        PasswordField("Password", password, { viewmodel.setPassword(it) }, passwordVisibility)
        PasswordField("Confirm Password", confirmPassword, { viewmodel.setConfirmPassword(it) }, passwordVisibility)

        Spacer(modifier = Modifier.height(10.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { viewmodel.signUp() }) {
                Text("Sign Up")
            }
            Button(onClick = { navController.navigate(Router.Account.route) }) {
                Text("Log In")
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Text("                           ")
            Text("Already have an account?")
        }

        AuthStatus(signupState, errorMessage, "Processing signup...")
    }

    if (signupState.value == AuthState.Success) {
        navController.navigate(Router.Home.route)
    }
}
