package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.ui.FreshTomatoes

/**
 * MovieEditor implements the functionality to edit a user's MovieReview. The review and rating
 * fields are editable. User has the option to save their changes or delete the review entirely.
 *
 * @param movieReview Target review to edit.
 * @param editCallback Callback function when the user edits the review. Defaults to an empty function.
 * @param deleteCallback Callback function when the user deletes the review. Defaults to an empty function.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieEditor(
    movieReview: MovieReview,
    editCallback: () -> Unit = { },
    deleteCallback: () -> Unit = { }
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedRating by remember {
        mutableStateOf(movieReview.review.rating)
    }

    var reviewText by remember {
        mutableStateOf(movieReview.review.review)
    }

    Row(modifier = Modifier.padding(10.dp)) {
        OutlinedTextField(
            modifier = Modifier
                .width(250.dp),
            value = reviewText,
            label = { Text("Review") },
            onValueChange = { reviewText = it }
        )

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = "$selectedRating",
                label = { Text("Rating") },
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor().padding(start = 8.dp)
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                for (i in 1..5) {
                    DropdownMenuItem(text = { Text("$i") }, onClick = {
                        selectedRating = i
                        expanded = false
                    })
                }
            }
        }
    }
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val oldReview = movieReview.review
        Button(onClick = {
            FreshTomatoes.appModule.reviewRepository.editReview(oldReview, Review(oldReview.movieId, reviewText, selectedRating, oldReview.ownerUID, oldReview.reviewDate))
            editCallback()
        }) {
            Text("Save Changes")
        }
        Button(onClick = {
            FreshTomatoes.appModule.reviewRepository.deleteReview(oldReview)
            deleteCallback()
        }) {
            Text("Delete")
        }
    }
}
