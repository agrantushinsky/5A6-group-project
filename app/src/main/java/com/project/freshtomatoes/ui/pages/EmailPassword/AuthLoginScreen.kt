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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column (modifier = Modifier.padding(20.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        if (userState.value == null) {
            Text("Not logged in", fontSize = 24.sp, modifier = Modifier.padding(20.dp))
            //add the email placeholder inside the textfield
            TextField(value = email, onValueChange = { email = it }, modifier = Modifier.padding(20.dp))
            Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
                Button(onClick = {
                    authViewModel.signUp("$email", "Abcd1234!")
                }) {
                    Text("Sign up")
                }
                Button(onClick = {
                    authViewModel.signIn("$email", "Abcd1234!")
                }) {
                    Text("Sign in")
                }
            }


        } else {
            if (userState.value==null)
                Text("Please sign in")
            else
                Text("Welcome ${userState.value!!.email}")

            Row {
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
}
