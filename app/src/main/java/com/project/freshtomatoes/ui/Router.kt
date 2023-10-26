package com.project.freshtomatoes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.pages.About.AboutUs
import com.project.freshtomatoes.ui.pages.EmailPassword.AuthLoginScreen
import com.project.freshtomatoes.ui.pages.Home.Home
import com.project.freshtomatoes.ui.pages.Movies.Movie

sealed class Router(val route: String) {
    object Home : Router("Home")
    object About : Router("About")
    object Movie : Router("Movie")
    object Account : Router("Account")
}

@Composable
fun NavGraph() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "Home") {
        composable(Router.Home.route) { Home() }
        composable(Router.About.route) { AboutUs() }
        composable(Router.Movie.route) { Movie() }
        composable(Router.Account.route){ AuthLoginScreen()}
    }
}
