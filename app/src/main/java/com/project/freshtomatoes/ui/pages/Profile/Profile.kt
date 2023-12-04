package com.project.freshtomatoes.ui.pages.Profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.Router
import com.project.freshtomatoes.ui.factories.AuthViewModelFactory
import com.project.freshtomatoes.ui.factories.ProfileViewModelFactory
import com.project.freshtomatoes.ui.pages.EmailPassword.AuthViewModel
import java.text.SimpleDateFormat

@Composable
fun Profile(
    viewmodel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory()),
    authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())
) {
    val userState = authViewModel.currentUser().collectAsState()
    val navController = LocalNavController.current
    if (userState.value == null) {
        return
    }

    viewmodel.updateYourReviews()
    val sortedMovies = viewmodel.yourReviews.collectAsState()

    Column {
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome ${userState.value?.email?.split("@")?.get(0)}!", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(20.dp))
            Divider(thickness = 5.dp)
        }

        Text(text = "User Information", fontSize = 5.em, modifier = Modifier.padding(5.dp))
        Card(
            modifier = Modifier
                .height(300.dp)
                .width(200.dp)
        ) {
            Card(modifier = Modifier.padding(8.dp)) {
                Text(text = "Email", fontWeight = FontWeight.Bold)
                Text(text = "${userState.value?.email}")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Card(modifier = Modifier.padding(8.dp)) {
                Text(text = "Date Joined ", fontWeight = FontWeight.Bold)
                val formatter = SimpleDateFormat("MMMM d, yyyy")
                Text(text = "${formatter.format(userState.value?.dateJoined)}")
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                authViewModel.signOut()
                navController.navigate(Router.Home.route)
            }) {
                Text("Sign Out")
            }
            Button(onClick = {
                authViewModel.delete()
            }) {
                Text("Delete account")
            }
        }
    }
}
