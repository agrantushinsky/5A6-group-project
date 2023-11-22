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
import com.project.freshtomatoes.data.Review

@Composable
fun MovieReviewCard(review : Review)
{
    Card(modifier = Modifier.height(50.dp).padding(10.dp).fillMaxWidth()){
        Row{
            Column{
                Card {
                    //movie image
                    AsyncImage(model = null, contentDescription = null)
                }
            }
            Column{
                Row{
                    Text("Rating: ${review.rating}/5")
                }
                Row{
                    Text("Comments: ${review.review}")
                }
            }
        }
    }
}