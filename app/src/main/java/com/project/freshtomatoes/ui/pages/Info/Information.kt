package com.project.freshtomatoes.ui.pages.Info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Information() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("Information about Fresh Tomatoes", fontSize = 7.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp, 8.dp), fontWeight = FontWeight.Bold)
        Text("Overview 🍅", fontSize = 6.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp, 8.dp), fontWeight = FontWeight.Bold)
        Text("You can browse movies, see it's details and see it's reviews without signing in. The average is calculated based off the apps users reviews.", fontSize = 5.em, textAlign = TextAlign.Start, modifier = Modifier.padding(15.dp, 8.dp))
        Text("While signed in you are able to review, edit and delete movie reviews you have created. You will also get access to a profile page which will display information about your profile as well as a page to see all the reviews you have made.", fontSize = 5.em, textAlign = TextAlign.Start, modifier = Modifier.padding(15.dp, 8.dp))
        Text("Getting Started 🍅", fontSize = 6.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp, 8.dp), fontWeight = FontWeight.Bold)
        Text("To Sign up / Log in you can click the profile icon on the top right of the screen. To see movie details you can click on the movie poster on the home page from there you can rate a movie. If wanting to edit or delete the review you can go to the Your Reviews page which is located on the bottom and do so.", fontSize = 5.em, textAlign = TextAlign.Start, modifier = Modifier.padding(15.dp, 8.dp))
    }
}
