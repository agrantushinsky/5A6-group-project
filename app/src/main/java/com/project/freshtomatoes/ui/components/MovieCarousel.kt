package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.project.freshtomatoes.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MovieCarousel(label: String, movieQuery: CoroutineScope.() -> List<Movie>) {
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(0) {
        scope.launch(Dispatchers.IO) {
            movieList = movieQuery()
        }
    }

    Column {
        Text(label)

        LazyRow {
            items(movieList) { movie ->
                MovieCard(movie)
            }
        }
    }
}
