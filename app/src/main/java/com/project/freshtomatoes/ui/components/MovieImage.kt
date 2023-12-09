package com.project.freshtomatoes.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.Movie

//Coded by Nitpreet
/**
 * MovieImage displays the movie poster from the relevant movie.
 * Image is fetched from https://image.tmdb.org/t/p/w500/.
 *
 * @param movie Movie to display poster for.
 * @param modifier Modifier parameter. Defaulted to Modifier.
 */
@Composable
fun MovieImage(movie: Movie, modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
        contentDescription = "${movie.title}'s movie poster image",
        modifier = modifier
    )
}
