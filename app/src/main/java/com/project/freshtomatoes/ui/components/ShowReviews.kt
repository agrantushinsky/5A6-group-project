package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.project.freshtomatoes.data.MovieReview

/**
 * ShowReviews displays a list of movie reviews using MovieReviewCard.
 *
 * @param reviews The list of the movie reviews to display.
 */
@Composable
fun ShowReviews(reviews: List<MovieReview>,message : String) {
    if (reviews.isEmpty()) {
        Text("There are no reviews.", fontSize = 7.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp,8.dp), fontWeight = FontWeight.Bold)
        return
    }
    Column {
        Text(text = message, fontSize = 7.em, textAlign = TextAlign.Center, modifier = Modifier.padding(15.dp,8.dp), fontWeight = FontWeight.Bold)
        LazyColumn {
            items(reviews) {
                MovieReviewCard(it)
            }
        }
    }

}
