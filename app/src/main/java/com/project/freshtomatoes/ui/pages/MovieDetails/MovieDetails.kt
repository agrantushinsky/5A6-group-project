package com.project.freshtomatoes.ui.pages.MovieDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.Genres
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun MovieDetails(id: Int) {
    if (id == -1) {
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

    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "${movie?.title}", fontSize = 7.em, modifier = Modifier.width(250.dp))
            Text(text = "Average: 3/5🍅", fontSize = 5.em)
        }
        Text(text = "${movie?.tagline}")
        Spacer(modifier = Modifier.padding(5.dp))
        movie?.let { DisplayList(it.genres) }
        Spacer(modifier = Modifier.padding(5.dp))
        Divider(thickness = 3.dp)
        Box(
            modifier = Modifier
                .height(350.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500/${movie?.poster_path}",
                contentDescription = "Translated description of what the image contains",
                modifier = Modifier
                    .height(300.dp)
                    .width(250.dp)
            )
        }
        Text(text = "Overview:", fontSize = 5.em)
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = "${movie?.overview}")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Rate Movie")
        }
    }
}

@Composable
fun DisplayList(generes: List<Genres>) {
    LazyRow() {
        items(generes) {
                genre ->
            Button(
                onClick = { /*TODO*/ },
                border = BorderStroke(1.dp, Color(0xFFC00100)),
                shape = RoundedCornerShape(80),
                modifier = Modifier.height(35.dp)
            ) {
                Text(text = "${genre.name}", fontSize = 4.em)
            }
        }
    }
}
