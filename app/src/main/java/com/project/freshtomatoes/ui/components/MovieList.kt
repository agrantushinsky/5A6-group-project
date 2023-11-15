package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.ui.Router

@Composable
fun MovieList(expanded: MutableState<Boolean>, movieList: List<Movie>)
{
    val navController = LocalNavController.current;

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier.height(240.dp).fillMaxWidth(),
        properties = PopupProperties(focusable = false)
    ) {
        movieList.forEach { movie ->
            DropdownMenuItem(
                text = { Text(text = "${movie.title}") },
                onClick = { navController.navigate(Router.MovieDetails.go(movie.id))},
                leadingIcon = {
                    IconButton(onClick = { }) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                            contentDescription = "Translated description of what the image contains"
                        )
                    }
                }
            )
        }
    }
}
