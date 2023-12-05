package com.project.freshtomatoes.ui.pages.Info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Information()
{
Column {
    Text("Information about Fresh Tomatoes", fontSize = 7.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp, 8.dp), fontWeight = FontWeight.Bold)
    Text("You can browse movies, see it's details and see it's reviews without signing in. The average is calculated based off the apps users reviews.",fontSize = 5.em, textAlign = TextAlign.Start, modifier = Modifier.padding(15.dp, 8.dp))
    Text("While signed in you are able to review, edit and delete movie reviews you have created. You will also get access to a profile page which will display information about your profile as well as a page to see all the reviews you have made.",fontSize = 5.em, textAlign = TextAlign.Start, modifier = Modifier.padding(15.dp, 8.dp))
}
}