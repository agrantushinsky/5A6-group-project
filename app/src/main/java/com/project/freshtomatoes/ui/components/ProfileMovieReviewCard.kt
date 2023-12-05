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


@SuppressLint("SimpleDateFormat")
@Composable
fun ProfileMovieCard(movie: MovieReview) {

            Column(modifier = Modifier.padding(15.dp)) {
                MovieImage(movie.movie)
                Text("${movie.review.rating}/5 🍅")
                val formatter = SimpleDateFormat("MM/d/yyyy")
                Text(text = "${formatter.format(Date(movie.review.reviewDate))}")
                
            }

}
