package com.project.freshtomatoes.ui.pages.Movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.project.freshtomatoes.R

@Composable
fun Movie() {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = "Here is your movie list", fontSize = 5.em)
        Spacer(modifier = Modifier.height(15.dp))
        Card(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "Movie title: (A movie you added")
                Text(text = "Rating: (A Rating you added")
            }
        }
    }
}
