package com.project.freshtomatoes.ui.pages.GenrePage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.components.MovieDetailsList
import com.project.freshtomatoes.ui.factories.GenreViewModelFactory

/**
 * Genre page to display a list o movies for a given genreId.
 *
 * @param genreId GenreID to display movies for.
 * @param viewmodel ViewModel to retrieve movies. Defaulted to viewModel using GenreViewModelFactory.
 */
@Composable
fun Genre(genreId: Int, viewmodel: GenreViewModel = viewModel(factory = GenreViewModelFactory())) {
    val movies = viewmodel.movies.collectAsState()
    viewmodel.updateMovies(genreId)

    MovieDetailsList(movies.value)
}
