package com.project.freshtomatoes.ui.pages.YourReviews

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.em
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.data.Review
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.factories.MovieDetailsViewModelFactory
import com.project.freshtomatoes.ui.factories.YourReviewsViewModelFactory
import com.project.freshtomatoes.ui.layout.items
import com.project.freshtomatoes.ui.viewmodels.MovieDetailsViewModel
import kotlinx.coroutines.flow.collect

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun YourReviews(viewmodel: YourReviewsViewModel = viewModel(factory = YourReviewsViewModelFactory())) {
    var reviews = viewmodel.yourReviews.collectAsState()
    val currentUser = FreshTomatoes.appModule.authRepository.currentUser().value
    viewmodel.updateYourReviews(currentUser!!.uid)
    if (currentUser != null) {
        ShowReviews(reviews.value)
    } else {
        ShowUserNotLoggedIn()
    }
}
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShowReviews(reviews: List<Review>) {
    LazyColumn {
        items(reviews){
            Text("${it.rating} - ${it.review}")
        }
    }
}

@Composable
fun ShowUserNotLoggedIn() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Must login to see your reviews ", fontSize = 4.em)
    }
}
