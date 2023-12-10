package com.project.freshtomatoes.ui.pages.Review

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.components.MovieImage
import com.project.freshtomatoes.ui.factories.ReviewViewModelFactory
import io.ktor.util.date.toDate
import io.ktor.util.date.toJvmDate
import java.util.Calendar

// Coded by Nitpreet
/**
 * Review page to facilitate posting reviews. Allows the user to create their tomato rating, and
 * write their review text.
 *
 * @param id Movie id to post reviews for.
 * @param viewmodel Viewmodel for the review creation page.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Review(id: Int, viewmodel: ReviewViewModel = viewModel(factory = ReviewViewModelFactory())) {
    val navController = LocalNavController.current
    val movie by viewmodel.movie.collectAsState()
    viewmodel.updateMovie(id)

    val rating = viewmodel.rating.collectAsState()
    val reviewText = viewmodel.review.collectAsState()

    if (movie == null) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //region Top Part of Page
        Row() {
            Text(text = "${movie!!.title}", fontSize = 7.em)
        }
        Divider()
        //endregion
        //region Tomatoes + Image
        Spacer(modifier = Modifier.height(25.dp))
        MovieImage(
            movie!!,
            modifier = Modifier
                .height(300.dp)
                .width(250.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = rating.value, fontSize = 10.em)
        Spacer(modifier = Modifier.height(20.dp))
        //endregion
        //region Buttons
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { viewmodel.throwTomato() }
            ) {
                Text(text = "Throw")
            }

            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { viewmodel.growTomato() }) {
                Text(text = "Grow")
            }
        }
        //endregion

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            placeholder = { Text(text = "Write a review of the Movie") },
            value = reviewText.value,
            onValueChange = { viewmodel.setReview(it) },
            modifier = Modifier
                .height(200.dp)
                .width(450.dp)
        )

        Button(onClick = {
            viewmodel.postReview(
                Review(
                    movie!!.id,
                    reviewText.value,
                    rating.value.length / 2, // Emojis are two characters.
                    FreshTomatoes.appModule.authRepository.currentUser().value!!.uid,
                    Calendar.getInstance().toDate(Calendar.getInstance().timeInMillis + 18000000).toJvmDate().toString()
                )
            )
            navController.popBackStack()
        }) {
            Text("Post Review")
        }
    }
}
