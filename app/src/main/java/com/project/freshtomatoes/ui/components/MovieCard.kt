package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.ui.Router

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
