package com.project.freshtomatoes.ui.pages.YourReviews

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.project.freshtomatoes.R
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.Router

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun YourReviews() {
        if(FreshTomatoes.appModule.authRepository.currentUser().value != null)
        {
           ShowReviews()
        }
        else
        {
            ShowUserNotLoggedIn()
        }

}

@Composable
fun ShowReviews()
{

}

@Composable
fun ShowUserNotLoggedIn()
{
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Must login to see your reviews ", fontSize = 4.em)
    }

}
