package com.project.freshtomatoes.ui.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.Router
import com.project.freshtomatoes.ui.factories.AuthViewModelFactory

// Show back buttons on page that aren't "1 click" navigable.
private fun shouldShowBackButton(route: String?): Boolean {
    if (route == null) {
        return false
    }

    return !(
        route == Router.Home.route ||
            route == Router.YourReviews.route ||
            route == Router.About.route ||
            route == Router.Info.route ||
            route == Router.Profile.route
        )
}

/**
 * TopBar to be displayed at the top of the screen. Shows FreshTomatoes header and login status.
 * When logged out, a sign in button that navigates to the login page is shown, otherwise your username
 * will be shown with a navigation link to your user profile.
 *
 * @param authViewModel The authentication viewmodel, defaulted to viewModel using AuthViewModelFactory.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory())) {
    val navController = LocalNavController.current
    val userState = authViewModel.currentUser().collectAsState()
    val backStack = navController.currentBackStack.collectAsState()

    TopAppBar(
        title =
        {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                if (shouldShowBackButton(backStack.value.lastOrNull()?.destination?.route)) {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back button")
                    }
                }
                Text(text = "FreshTomatoes")
                if (userState.value != null) {
                    Text(text = "${userState.value?.email?.split("@")?.get(0)}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            IconButton(
                onClick =
                {
                    if (userState.value == null) {
                        navController.navigate(Router.Account.route)
                    } else {
                        navController.navigate(Router.Profile.route)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account button"
                )
            }
        }
    )
}
