package com.project.freshtomatoes.ui.pages.Home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.data.Movie
import com.project.freshtomatoes.data.TmdbRequest
import com.project.freshtomatoes.ui.components.MovieCard
import com.project.freshtomatoes.ui.components.SearchBar
import com.project.freshtomatoes.ui.factories.HomeViewModelFactory
import com.project.freshtomatoes.ui.layout.items
import com.project.freshtomatoes.ui.viewmodels.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
