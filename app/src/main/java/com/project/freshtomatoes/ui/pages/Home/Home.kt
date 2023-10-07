package com.project.freshtomatoes.ui.pages.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun Home() {
    Column(modifier = Modifier.padding(15.dp)) {
        Text(text = "Welcome To the home page", fontSize = 5.em, )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add a movie to review")
        }
        
    }

    
    
    
    
}