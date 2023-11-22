package com.project.freshtomatoes.ui.pages.MovieReviews

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.components.ShowReviews
import com.project.freshtomatoes.ui.factories.MovieReviewsViewModelFactory

@Composable
fun MovieReviews(movieId: Int, viewmodel: MovieReviewsViewModel = viewModel(factory = MovieReviewsViewModelFactory())) {
    val reviews = viewmodel.movieReviews.collectAsState()
    viewmodel.updateMovieReviews(movieId)

    ShowReviews(reviews.value)
}
