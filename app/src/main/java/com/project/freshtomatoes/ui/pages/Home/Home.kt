package com.project.freshtomatoes.ui.pages.Home

import HomeViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.components.MovieCard
import com.project.freshtomatoes.ui.components.SearchBar
import com.project.freshtomatoes.ui.factories.HomeViewModelFactory

// Coded by Nipreet
/**
 * Home page for the app.
 *
 * @param viewmodel Viewmodel for page, defaulted to viewModel using HomeViewModelFactory.
 */
@Composable
fun Home(viewmodel: HomeViewModel = viewModel(factory = HomeViewModelFactory())) {
    Column(modifier = Modifier.padding(15.dp)) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            SearchBar()
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn {
            items(viewmodel.getAllMovies()) {
                Text(it.label)
                LazyRow {
                    items(it.movieList) { movie ->
                        MovieCard(movie)
                    }
                }
            }
        }
    }
}
