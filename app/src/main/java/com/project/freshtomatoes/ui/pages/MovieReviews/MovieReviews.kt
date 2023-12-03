package com.project.freshtomatoes.ui.pages.MovieReviews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.components.ShowReviews
import com.project.freshtomatoes.ui.factories.MovieReviewsViewModelFactory

@Composable
fun MovieReviews(movieId: Int, name : String? ,viewmodel: MovieReviewsViewModel = viewModel(factory = MovieReviewsViewModelFactory())) {
    val reviews = viewmodel.movieReviews.collectAsState()
    viewmodel.updateMovieReviews(movieId)


        ShowReviews(reviews.value,"All reviews for the movie $name")


}
