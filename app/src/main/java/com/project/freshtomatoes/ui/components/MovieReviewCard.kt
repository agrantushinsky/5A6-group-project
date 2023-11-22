package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.Review

@Composable
fun MovieReviewCard(movieReview : MovieReview) {
    Card(modifier = Modifier.height(100.dp).padding(10.dp).fillMaxWidth()){
        Row {
            Column{
                Card {
                    //movie image
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500/${movieReview.movie.poster_path}",
                        contentDescription = null
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Row{
                    Text("Rating: ${movieReview.review.rating}/5")
                }
                Row{
                    Text("Comments: ${movieReview.review.review}")
                }
            }
        }
    }
}