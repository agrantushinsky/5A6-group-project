package com.project.freshtomatoes.ui.pages.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun Home() {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = "Welcome To the home page", fontSize = 5.em)
        PopularMoviesList()
        NewMovies()
    }
}

@Composable
fun PopularMoviesList() {
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }

    runBlocking {
        launch {
            val requester = TmdbRequest()
            val response = requester.popularMovies()
            if (response.results.isNotEmpty()) {
                movieList = response.results
            }
        }
    }

    Column {
        Text(text = "Popular Movies")
        LazyRow {
            items(movieList) { movie ->
                MovieItem(movie)
            }
        }
    }
}

@Composable
fun NewMovies() {
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }

    runBlocking {
        launch {
            val requester = TmdbRequest()
            val response = requester.nowPlayingMovies()
            if (response.results.isNotEmpty()) {
                movieList = response.results
            }
        }
    }

    Column {
        Text(text = "New Movies")
        LazyRow {
            items(movieList) { movie ->
                MovieItem(movie)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(movie: Movie) {
    Card(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
            contentDescription = "Translated description of what the image contains"
        )
    }
}
