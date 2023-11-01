package com.project.freshtomatoes.ui.pages.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.components.MovieCard
import com.project.freshtomatoes.ui.components.SearchBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun Home() {
    Column(modifier = Modifier.padding(15.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            SearchBar()
        }

        Spacer(modifier = Modifier.height(15.dp))
        PopularMoviesList()
        NewMovies()
    }
}

@Composable
fun PopularMoviesList() {
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(0) {
        scope.launch(Dispatchers.IO) {
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
                MovieCard(movie)
            }
        }
    }
}

@Composable
fun NewMovies() {
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(0) {
        scope.launch(Dispatchers.IO) {
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
                MovieCard(movie)
            }
        }
    }
}
