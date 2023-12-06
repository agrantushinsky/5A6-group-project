package com.project.freshtomatoes.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.data.MovieReview
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
        val formatter = SimpleDateFormat("d/MM/yyyy")
        Text(text = "${formatter.format(Date(movie.review.reviewDate))}")
    }
}
