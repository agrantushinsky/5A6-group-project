package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var text by remember { mutableStateOf("") }
    var expanded = remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column {
        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                text = it
                if (text.isNotEmpty()) {
                    scope.launch(Dispatchers.IO) {
                        val requester = TmdbRequest()
                        val response = requester.searchMovies(text)
                        if (response.results.isNotEmpty()) {
                            movieList = response.results
                        }
                    }
                }
                expanded.value = movieList.isNotEmpty()
            },
            placeholder = { Text(text = "Search") },
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "This is a search button")
            }
        )

        // Only draw the movie list if the search found movies
        if (movieList.isNotEmpty()) {
            MovieList(expanded, movieList)
        }
    }
}
