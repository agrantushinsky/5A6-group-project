package com.project.freshtomatoes.ui.pages.Review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Review(id: Int)
{
    val scope = rememberCoroutineScope()
    var movie by remember { mutableStateOf<Movie?>(null) }
    var tomatoes by remember {
        mutableStateOf("🍅🍅🍅🍅🍅")
    }
    LaunchedEffect(0) {
        scope.launch(Dispatchers.IO) {
            val requester = TmdbRequest()
            val response = requester.details(id)
            movie = response
        }
    }
    if (movie == null) return

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row()
        {
            Text(text = movie!!.title, fontSize = 7.em)
        }
        Divider()
        Spacer(modifier = Modifier.height(100.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End)
        {
            /*
            TODO FIX THIS PART
             */
            Text(text = "Rating: ${tomatoes.length/20 * 100}%")
        }
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500/${movie?.poster_path}",
            contentDescription = "Translated description of what the image contains",
            modifier = Modifier
                .height(300.dp)
                .width(250.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = tomatoes,fontSize = 15.em)
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick =
            {
                if(tomatoes.isNotEmpty())
                {
                    tomatoes = tomatoes.substring(0, tomatoes.length - 2)
                }
            }) {
                Text(text = "Throw")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                if(tomatoes.length <10)
                {
                    tomatoes+= "🍅"
                }
            }) {
                Text(text = "Grow")
            }
        }
    }







}