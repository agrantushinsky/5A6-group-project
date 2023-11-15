package com.project.freshtomatoes.ui.pages.YourReviews

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import com.project.freshtomatoes.ui.FreshTomatoes

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun YourReviews() {
    if (FreshTomatoes.appModule.authRepository.currentUser().value != null) {
        ShowReviews()
    } else {
        ShowUserNotLoggedIn()
    }
}

@Composable
fun ShowReviews() {
}

@Composable
fun ShowUserNotLoggedIn() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Must login to see your reviews ", fontSize = 4.em)
    }
}
