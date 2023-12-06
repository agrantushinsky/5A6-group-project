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
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.ui.Router

/**
 * MovieList displays a clickable list of movies, which upon being clicked navigates to its
 * movie details page.
 *
 * @param expanded Mutable state for the list's expanded state.
 * @param movieList List of movies to display within the dropdown.
 */
@Composable
fun MovieList(expanded: MutableState<Boolean>, movieList: List<Movie>) {
    val navController = LocalNavController.current

    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false },
        modifier = Modifier.height(240.dp).fillMaxWidth(),
        properties = PopupProperties(focusable = false)
    ) {
        movieList.forEach { movie ->
            DropdownMenuItem(
                text = { Text(movie.title) },
                onClick = { navController.navigate(Router.MovieDetails.go(movie.id)) },
                leadingIcon = {
                    IconButton(onClick = { }) {
                        MovieImage(movie)
                    }
                }
            )
        }
    }
}
