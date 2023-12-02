package com.project.freshtomatoes.ui.pages.MovieDetails

import android.annotation.SuppressLint
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Genre
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.Router
import com.project.freshtomatoes.ui.components.ErrorDialog
import com.project.freshtomatoes.ui.components.GenreList
import com.project.freshtomatoes.ui.factories.MovieDetailsViewModelFactory
import java.text.NumberFormat
import java.util.Locale

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MovieDetails(id: Int, viewmodel: MovieDetailsViewModel = viewModel(factory = MovieDetailsViewModelFactory())) {
    if (id == -1) {
        Text("A fatal error has occurred.")
        return
    }
    var showErrorDialog by remember { mutableStateOf(false) }
    var showReviewd by remember {
        mutableStateOf(false)
    }
    val navController = LocalNavController.current

    val movie by viewmodel.movie.collectAsState()
    val averageRating by viewmodel.averageRating.collectAsState()
    val cf = NumberFormat.getCurrencyInstance(Locale.US)

    if (movie == null) {
        viewmodel.updateMovie(id)
        return
    }
    if(FreshTomatoes.appModule.authRepository.currentUser().value != null)
    {
        viewmodel.getIfReviewed(FreshTomatoes.appModule.authRepository.currentUser().value!!.uid,movie!!.id)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "${movie?.title}",
                fontSize = 7.em,
                modifier = Modifier.width(200.dp),
                style = TextStyle(lineHeight = 1.2.em)
            )
            if(averageRating == null || averageRating!!.isNaN()) {
                Text(text = "Not yet reviewed", fontSize = 5.em)
            } else {
                Text(text = "Average: ${String.format("%.1f", averageRating)}🍅", fontSize = 5.em)
            }
        }
        Text(text = "${movie?.tagline}")
        Spacer(modifier = Modifier.padding(5.dp))
        movie?.let { GenreList(it.genres) }
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

        Divider()
        Text(text = "Overview:", fontSize = 5.em)
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = "${movie?.overview}")
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "More Details:", fontSize = 5.em)
        Spacer(modifier = Modifier.padding(2.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Column {
                Text(text = "Released On: ${movie?.release_date}")
                Text(text = "Length: ${movie?.runtime} min")
                Text(text = "Revenue: ${ cf.format(movie?.revenue)}")
            }
            Column {
                Button(
                    onClick =
                    {
                        if (FreshTomatoes.appModule.authRepository.currentUser().value != null) {

                            if(!viewmodel.reviewed.value) {
                                navController.navigate(Router.Review.go(id))
                            }
                            else
                            {
                                showReviewd = true
                            }
                        } else {
                            showErrorDialog = true
                        }
                    }
                ) {
                    Text("Rate Movie")
                }

                Button(onClick = {
                    navController.navigate(Router.MovieReviews.go(id))
                }) {
                    Text("See Reviews")
                }

                if (showErrorDialog) {
                    ErrorDialog("Must sign in","In order to rate the movie you must sign in.") {
                        showErrorDialog = false
                    }
                }
                if (showReviewd) {
                    ErrorDialog("Already Reviewed","You have already rated ${movie!!.title}") {
                        showReviewd = false
                    }
                }
            }
        }
    }
}


