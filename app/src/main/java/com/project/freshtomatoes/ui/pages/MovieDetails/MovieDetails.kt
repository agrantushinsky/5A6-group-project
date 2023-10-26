package com.project.freshtomatoes.ui.pages.MovieDetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MovieDetails(id: Int) {
    if(id == -1) {
        // TODO;
    }

    val scope = rememberCoroutineScope()
    var movie by remember { mutableStateOf<Movie?>(null) }
    LaunchedEffect(0) {
        scope.launch(Dispatchers.IO) {
            val requester = TmdbRequest()
            val response = requester.details(id)
            movie = response
        }
    }
    println(movie)



}