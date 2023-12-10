package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.ui.Router

// Coded by Aidan
/**
 * Displays a list of detailed movie cards, with clickable navigation to details page.
 *
 * @param movies Movie(s) list to display.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsList(movies: List<Movie>) {
    val navController = LocalNavController.current

    LazyColumn {
        items(movies) {
            // Card with image and basic details. Clickable to relevant details page.
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                onClick = { navController.navigate(Router.MovieDetails.go(it.id)) }
            ) {
                Row {
                    MovieImage(it, modifier = Modifier.height(100.dp))
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(it.title, fontWeight = FontWeight.Bold)
                        Text("Released: ${it.release_date}")
                    }
                }
            }
        }
    }
}
