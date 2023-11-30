package com.project.freshtomatoes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.freshtomatoes.data.MovieReview
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.ui.FreshTomatoes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieReviewCard(movieReview: MovieReview)
{
    val currentUser = FreshTomatoes.appModule.authRepository.currentUser().collectAsState()

    var edit by remember {
        mutableStateOf(movieReview.review.review)
    }

    var dropdown by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        //.height(100.dp)
        .padding(10.dp)
        .fillMaxWidth()) {
        Column (modifier = Modifier.fillMaxHeight()){
            Row {
                Column {
                    Card (modifier = Modifier.height(100.dp)){
                        // movie image
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${movieReview.movie.poster_path}",
                            contentDescription = null
                        )
                    }
                }
                Column(modifier = Modifier.padding(8.dp).width(250.dp)) {
                    Text("${movieReview.movie.title}", fontWeight = FontWeight.Bold)
                    Text("Rating: ${movieReview.review.rating}/5")
                    Text("Comments: ${movieReview.review.review}")

                }
                Column()
                {
                    if (currentUser.value != null && currentUser!!.value!!.uid == movieReview.review.ownerUID) {
                        if (dropdown) {
                            IconButton(onClick = { dropdown = !dropdown }) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowDown,
                                    contentDescription = "ArrowDown"
                                )
                            }
                        } else {
                            IconButton(onClick = { dropdown = !dropdown }) {
                                Icon(
                                    imageVector = Icons.Filled.KeyboardArrowUp,
                                    contentDescription = "ArrowUp"
                                )
                            }
                        }
                    }


                }
//            Row(modifier = Modifier.weight(1f)) {
//                Button(onClick = { /*TODO*/ }) {
//
//                    Icon(imageVector = Icons.Filled.Create, contentDescription =  "edit")
//                }
//                Button(onClick = { /*TODO*/ }) {
//                    Icon(imageVector = Icons.Filled.Delete,contentDescription =  "edit" )
//                }
//            }
            }
            if (dropdown) {
                var expanded by remember { mutableStateOf(false)}
                var selectedRating by remember {
                    mutableStateOf(movieReview.review.rating)
                }
                Row (modifier = Modifier.padding(10.dp))
                {
                    OutlinedTextField(modifier = Modifier
                        .width(250.dp)
                        .padding(5.dp),value = edit, label = {Text(text = "Review Edit")}, onValueChange = {edit= it})
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
                                    DropdownMenuItem(text={Text("$i")}, onClick = {
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
                    val oldReview = movieReview.review;
                    Button(onClick = {
                        FreshTomatoes.appModule.reviewRepository.editReview(oldReview, Review(oldReview.movieId, edit, selectedRating, oldReview.ownerUID, oldReview.reviewDate ))
                        dropdown = false;
                    }) {
                        Icon(imageVector = Icons.Filled.Create, contentDescription =  "edit")
                    }
                    Button(onClick = { FreshTomatoes.appModule.reviewRepository.deleteReview(oldReview) }) {
                        Icon(imageVector = Icons.Filled.Delete,contentDescription =  "delete" )
                    }
                }
            }
        }
    }
}
