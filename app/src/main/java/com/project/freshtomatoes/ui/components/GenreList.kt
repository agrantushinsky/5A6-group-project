package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Genre
import com.project.freshtomatoes.ui.Router

// Coded by Nitpreet
/**
 * GenreList displays a list of Genre in a row.
 *
 * @param genres List of Genre(s) to display.
 */
@Composable
fun GenreList(genres: List<Genre>) {
    val navController = LocalNavController.current
    LazyRow(modifier = Modifier.padding(10.dp)) {
        items(genres) {
                genre ->
            Button(
                onClick = { navController.navigate(Router.GenreMovieList.go(genre.id)) },
                border = BorderStroke(1.dp, Color(0xFFC00100)),
                shape = RoundedCornerShape(80),
                modifier = Modifier.height(45.dp)
            ) {
                Text(genre.name, fontSize = 4.em)
            }
        }
    }
}
