package com.project.freshtomatoes.ui.pages.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun Home() {
    Column(modifier = Modifier.padding(15.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
        {
            SearchBar()
        }
        Spacer(modifier = Modifier.height(15.dp))
        PopularMoviesList()
        NewMovies()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(){
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false)}
    Column {
        TextField(value = text,
            modifier = Modifier.fillMaxWidth(),
            onValueChange =
            {
                text = it
                if(text.isNotEmpty()){
                    scope.launch(Dispatchers.IO) {
                        val requester = TmdbRequest()
                        val response = requester.searchMovies(text)
                        if (response.results.isNotEmpty()) {
                            movieList = response.results
                        }
                    }
                }
                expanded = !movieList.isEmpty()
            },
            placeholder = {Text(text = "Search")},
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "This is a search button" )
                }
            }
        )
        if (movieList.isNotEmpty())
        {
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false },modifier = Modifier.height(240.dp).fillMaxWidth(),properties = PopupProperties(focusable = false)) {
                movieList.forEachIndexed { index, movie ->
                    DropdownMenuItem(text = { Text(text = "${movie.title}" )}, onClick = { /*TODO*/ }, leadingIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w500/${movie.poster_path}",
                                contentDescription = "Translated description of what the image contains"
                            )
                        }
                    })
                }

            }
        }
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
                MovieItem(movie)
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
