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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.FreshTomatoes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Review(id: Int)
{
    //region variables
    val scope = rememberCoroutineScope()
    var movie by remember { mutableStateOf<Movie?>(null) }
    var tomatoes by remember {
        mutableStateOf("🍅🍅🍅🍅🍅")
    }
    var tempString by remember {
        mutableStateOf("")
    }

    LaunchedEffect(0) {
        scope.launch(Dispatchers.IO) {
            val requester = TmdbRequest()
            val response = requester.details(id)
            movie = response
        }
    }
    //endregion

    if (movie == null) return

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally)

    {
        //region Top Part of Page
        Row()
        {
            Text(text = "🍅  ${movie!!.title}   🍅", fontSize = 7.em)
        }
        Divider()
        //endregion
        //region Rating Portion
        Spacer(modifier = Modifier.height(75.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End)
        {
            Column {
                Text(text = "Your Rating: ${((tomatoes.length.toDouble()/10)* 100).toInt()}%")
                Text(text = "Average Rating /*Todo*/")
            }
            
        }
        //endregion
        //region Tomatoes + Image
        Spacer(modifier = Modifier.height(25.dp))
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
        //endregion
        //region Buttons
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
        //endregion

        Spacer(modifier = Modifier.height(20.dp))
        TextField(placeholder = { Text(text = "Write a review of the Movie")},value = tempString, onValueChange = {tempString = it }, modifier = Modifier
            .height(200.dp)
            .width(450.dp))

        Button(onClick = {
            FreshTomatoes.appModule.reviewRepository.saveReview(
                Review(
                    movie!!.id,
                    tempString,
                    tomatoes.length,
                    FreshTomatoes.appModule.authRepository.currentUser().value!!.uid
                )
            )
        }) {
            Text("REVIEW!!!")
        }
    }
}
