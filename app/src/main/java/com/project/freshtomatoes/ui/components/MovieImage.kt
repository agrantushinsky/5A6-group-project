package com.project.freshtomatoes.ui.components

import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.Movie

@Composable
fun MovieImage(movie: Movie) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
        contentDescription = "${movie.title}'s movie poster image"
    )
}
