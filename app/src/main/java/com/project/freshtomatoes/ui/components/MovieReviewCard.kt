package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.ui.FreshTomatoes

/**
 * MovieReviewCard to represent a user's movie review. Card will show the relevant review, rating, and a
 * dropdown to display a MovieEditor.
 *
 * @param movieReview Movie review to display.
 */
@Composable
fun MovieReviewCard(movieReview: MovieReview) {
    val currentUser = FreshTomatoes.appModule.authRepository.currentUser().collectAsState()

    var dropdown by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Row {
                Column {
                    Card(modifier = Modifier.height(100.dp)) {
                        MovieImage(movieReview.movie)
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .width(250.dp)
                ) {
                    Text(movieReview.movie.title, fontWeight = FontWeight.Bold)
                    Text("Rating: ${movieReview.review.rating}/5")
                    Text("Comments: ${movieReview.review.review}")
                }
                Column() {
                    // Only display the arrow to expand the editor
                    // if we are logged in and the logged in user is the owner of review.
                    if (currentUser.value != null &&
                        currentUser.value!!.uid == movieReview.review.ownerUID
                    ) {
                        IconButton(onClick = { dropdown = !dropdown }) {
                            Icon(
                                imageVector = if (dropdown) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                                contentDescription = if (dropdown) "ArrowDown" else "ArrowUp"
                            )
                        }
                    }
                }
            }
            if (dropdown) {
                // Show editor when dropdown is exposed.
                // Also set dropdown to false when a edit occurs.
                MovieEditor(movieReview, editCallback = { dropdown = false }, deleteCallback = { dropdown = false })
            }
        }
    }
}
