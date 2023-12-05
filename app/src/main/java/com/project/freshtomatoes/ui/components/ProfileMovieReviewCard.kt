package com.project.freshtomatoes.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.ui.Router
import java.text.SimpleDateFormat
import java.util.Date

/**
 * ProfileMovieCard is a card that is used to display a basic summary
 * of a movie review on a user's profile.
 * Contains:
 * - Movie poster
 * - Rating
 * - Review date
 *
 * @param movie Movie review to display in the card.
 */
@SuppressLint("SimpleDateFormat")
@Composable
fun ProfileMovieCard(movie: MovieReview) {
    Column(modifier = Modifier.padding(15.dp)) {
        MovieImage(movie.movie, Modifier.height(200.dp))
        Text("${movie.review.rating}/5 🍅")
        val formatter = SimpleDateFormat("MM/d/yyyy")
        Text(text = "${formatter.format(Date(movie.review.reviewDate))}")

    }
}
