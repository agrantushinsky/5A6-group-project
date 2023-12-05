package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.SortState
import com.project.freshtomatoes.ui.factories.ShowReviewsViewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

/**
 * ShowReviews displays a list of movie reviews using MovieReviewCard.
 *
 * @param reviews The list of the movie reviews to display.
 */
@Composable
fun ShowReviews(
    sourceReviews: List<MovieReview>,
    message: String,
    viewmodel: ShowReviewsViewModel = viewModel(factory = ShowReviewsViewModelFactory())
) {
    viewmodel.updateList(sourceReviews)

    val reviews = viewmodel.movieReviews.collectAsState()
    val sortState = viewmodel.sortState.collectAsState()

    if (reviews.value.isEmpty()) {
        Text("There are no reviews.", fontSize = 7.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp, 8.dp), fontWeight = FontWeight.Bold)
        return
    }

    Column {
        Text(text = message, fontSize = 7.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp, 8.dp), fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.End) {
            ButtonDropDown(label = "Sorting",
                icon = Icons.Filled.Sort,
                currentState = sortState.value,
                { viewmodel.setSortState(it) },
                enumValues = SortState.values(),
                modifier = Modifier
                    .width(236.dp)
                    .padding(6.dp)
            )
        }

        LazyColumn {
            items(reviews.value) {
                MovieReviewCard(it)
            }
        }
    }
}
