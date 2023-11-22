package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.project.freshtomatoes.data.MovieReview

@Composable
fun ShowReviews(reviews: List<MovieReview>) {
    if(reviews.isEmpty()) {
        Text("No reviews.")
        return
    }

    LazyColumn {
        items(reviews) {
            MovieReviewCard(it)
        }
    }
}
