package com.project.freshtomatoes.ui.pages.MovieReviews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.components.ShowReviews
import com.project.freshtomatoes.ui.factories.MovieReviewsViewModelFactory

/**
 * MovieReviews page to show reviews for a given movie using ShowReviews.
 *
 * @param movieId Movie ID for show reviews for.
 * @param name Name to display in the header.
 * @param viewmodel Viewmodel for MovieReviews page.
 */
@Composable
fun MovieReviews(movieId: Int, name: String?, viewmodel: MovieReviewsViewModel = viewModel(factory = MovieReviewsViewModelFactory())) {
    val reviews = viewmodel.movieReviews.collectAsState()
    viewmodel.updateMovieReviews(movieId)

    ShowReviews(reviews.value, "All reviews for the movie $name")
}
