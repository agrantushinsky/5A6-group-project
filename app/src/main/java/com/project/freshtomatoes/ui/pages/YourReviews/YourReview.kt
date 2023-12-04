package com.project.freshtomatoes.ui.pages.YourReviews

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.ui.FreshTomatoes
import com.project.freshtomatoes.ui.components.ShowReviews
import com.project.freshtomatoes.ui.components.ShowUserNotLoggedIn
import com.project.freshtomatoes.ui.factories.YourReviewsViewModelFactory


/**
 * YourReviews displays all the review for the currently logged in user, using ShowReviews.
 *
 * @param viewmodel Viewmodel for the YourReviews page.
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun YourReviews(viewmodel: YourReviewsViewModel = viewModel(factory = YourReviewsViewModelFactory())) {
    val reviews = viewmodel.yourReviews.collectAsState()
    viewmodel.updateYourReviews()
    if (viewmodel.shouldShowReviews()) {
        Column {
            ShowReviews(reviews.value, "Here are all the movies you reviewed ")
        }
    } else {
        ShowUserNotLoggedIn()
    }
}
