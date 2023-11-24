package com.project.freshtomatoes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.pages.About.AboutUs
import com.project.freshtomatoes.ui.pages.DeepScreen
import com.project.freshtomatoes.ui.pages.EmailPassword.AuthLoginScreen
import com.project.freshtomatoes.ui.pages.EmailPassword.AuthSignUpScreen
import com.project.freshtomatoes.ui.pages.Home.Home
import com.project.freshtomatoes.ui.pages.MovieDetails.MovieDetails
import com.project.freshtomatoes.ui.pages.MovieReviews.MovieReviews
import com.project.freshtomatoes.ui.pages.ProfileScreen
import com.project.freshtomatoes.ui.pages.Review.Review
import com.project.freshtomatoes.ui.pages.YourReviews.YourReviews

sealed class Router(val route: String) {
    object Home : Router("Home")
    object About : Router("About")
    object YourReviews : Router("Your Reviews")
    object MovieReviews : Router("MovieReviews/{id}") {
        fun go(id: Int) = "MovieReviews/$id"
    }
    object MovieDetails : Router("MovieDetails/{index}") {
        fun go(index: Int) = "MovieDetails/$index"
    }
    object Review : Router("Review/{index}") {
        fun go(index: Int) = "Review/$index"
    }

    object Account : Router("Account")
    object SignUp : Router("SignUp")
    object Profile : Router("Profile")
}

@Composable
fun NavGraph() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "Home") {
        composable(Router.Home.route) { Home() }
        composable(Router.About.route) { AboutUs() }
        composable(Router.YourReviews.route) { YourReviews() }
        composable(Router.MovieReviews.route) { MovieReviews(movieId = it.arguments?.getString("id")?.toInt() ?: -1) }
        composable(Router.MovieDetails.route) { MovieDetails(id = it.arguments?.getString("index")?.toInt() ?: -1) }
        composable(Router.Account.route) { AuthLoginScreen() }
        composable(Router.SignUp.route) { AuthSignUpScreen() }
        composable(Router.Review.route) { Review(id = it.arguments?.getString("index")?.toInt() ?: -1) }
        composable(Router.Profile.route) { ProfileScreen() }
        composable(
            "deeplink2?id={id}",
            // Note that this navDeepLink pattern has no relation to the route itself
            deepLinks = listOf(navDeepLink { uriPattern = "example://compose.deeplink2/?id={id}" })
        ) { backStackEntry ->
            DeepScreen(backStackEntry.arguments?.getString("id"))
        }
    }
}
