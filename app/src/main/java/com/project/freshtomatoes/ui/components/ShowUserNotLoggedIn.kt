package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em

/**
 * ShowUserNotLoggedIn shows the relevant message for when a user is not logged in. Text is centered.
 */
@Composable
fun ShowUserNotLoggedIn() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Must login to see your reviews ", fontSize = 4.em)
    }
}
