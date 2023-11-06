package com.project.freshtomatoes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.pages.About.AboutUs
import com.project.freshtomatoes.ui.pages.EmailPassword.AuthLoginScreen
import com.project.freshtomatoes.ui.pages.EmailPassword.AuthSignUpScreen
import com.project.freshtomatoes.ui.pages.Home.Home
import com.project.freshtomatoes.ui.pages.MovieDetails.MovieDetails
import com.project.freshtomatoes.ui.pages.Movies.Movie
import com.project.freshtomatoes.ui.pages.Review.Review

sealed class Router(val route: String) {
    object Home : Router("Home")
    object About : Router("About")
    object Movie : Router("Movie")
    object MovieDetails : Router("MovieDetails/{index}") {
        fun go(index: Int) = "MovieDetails/$index"
    }
    object Review : Router("Review/{index}") {
        fun go(index: Int) = "Review/$index"
    }

    object Account : Router("Account")
    object SignUp : Router("SignUp")
}

@Composable
fun NavGraph() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "Home") {
        composable(Router.Home.route) { Home() }
        composable(Router.About.route) { AboutUs() }
        composable(Router.Movie.route) { Movie() }
        composable(Router.MovieDetails.route) { MovieDetails(id = it.arguments?.getString("index")?.toInt() ?: -1) }
        composable(Router.Account.route) { AuthLoginScreen() }
        composable(Router.SignUp.route) { AuthSignUpScreen() }
        composable(Router.Review.route) { Review(id = it.arguments?.getString("index")?.toInt() ?: -1) }
    }
}
