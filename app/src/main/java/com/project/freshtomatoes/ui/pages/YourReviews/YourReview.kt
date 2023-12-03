package com.project.freshtomatoes.ui.pages.YourReviews

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.components.ShowReviews
import com.project.freshtomatoes.ui.components.ShowUserNotLoggedIn
import com.project.freshtomatoes.ui.factories.YourReviewsViewModelFactory

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun YourReviews(viewmodel: YourReviewsViewModel = viewModel(factory = YourReviewsViewModelFactory())) {
    val reviews = viewmodel.yourReviews.collectAsState()
    val currentUser = FreshTomatoes.appModule.authRepository.currentUser().value
    if (currentUser != null) {
        viewmodel.updateYourReviews(currentUser.uid)
        Column {
            ShowReviews(reviews.value,"Here are all the movies you reviewed ")
        }

    } else {
        ShowUserNotLoggedIn()
    }
}
