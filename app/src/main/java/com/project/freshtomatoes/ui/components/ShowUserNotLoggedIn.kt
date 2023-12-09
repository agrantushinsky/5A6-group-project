package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

//Coded by Jose
/**
 * ShowUserNotLoggedIn shows the relevant message for when a user is not logged in. Text is centered.
 */
@Composable
fun ShowUserNotLoggedIn() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Must login to see your reviews ", fontSize = 7.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp, 8.dp), fontWeight = FontWeight.Bold)
    }
}
