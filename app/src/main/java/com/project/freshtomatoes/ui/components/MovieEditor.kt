package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieEditor(movieReview: MovieReview, editCallback: () -> Unit = { }, deleteCallback: () -> Unit = { }) {
    var expanded by remember { mutableStateOf(false) }
    var selectedRating by remember {
        mutableStateOf(movieReview.review.rating)
    }

    var reviewText by remember {
        mutableStateOf(movieReview.review.review)
    }

    Row (modifier = Modifier.padding(10.dp))
    {
        OutlinedTextField(modifier = Modifier
            .width(250.dp)
            .padding(5.dp),value = reviewText, label = { Text(text = "Review") }, onValueChange = {reviewText= it})
        Box{
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded})
            {
                TextField(
                    value = "$selectedRating",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false})
                {
                    for(i in 1 .. 5)
                    {
                        DropdownMenuItem(text={ Text("$i") }, onClick = {
                            selectedRating = i
                            expanded = false
                        })
                    }
                }
            }
        }

    }
    Row(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly)
    {
        val oldReview = movieReview.review
        Button(onClick = {
            FreshTomatoes.appModule.reviewRepository.editReview(oldReview, Review(oldReview.movieId, reviewText, selectedRating, oldReview.ownerUID, oldReview.reviewDate ))
            editCallback()
        }) {
            Icon(imageVector = Icons.Filled.Create, contentDescription =  "edit")
        }
        Button(onClick = {
            FreshTomatoes.appModule.reviewRepository.deleteReview(oldReview)
            deleteCallback()
        }) {
            Icon(imageVector = Icons.Filled.Delete,contentDescription =  "delete" )
        }
    }
}
