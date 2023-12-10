package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.ui.Router
// Coded by Nitpreet
/**
 * Clickable MovieCard, which upon being clicked navigates to the MovieDetails page.
 * The movie's poster is displayed within the card.
 *
 * @param movie Movie to display & navigate.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(movie: Movie) {
    val navController = LocalNavController.current
    Card(
        onClick = { navController.navigate(Router.MovieDetails.go(movie.id)) },
        modifier = Modifier
            .padding(10.dp)
            .height(200.dp)
    ) {
        MovieImage(movie)
    }
}
