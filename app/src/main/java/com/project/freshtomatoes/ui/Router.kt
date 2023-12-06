package com.project.freshtomatoes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.pages.About.AboutUs
import com.project.freshtomatoes.ui.pages.DeepScreen
import com.project.freshtomatoes.ui.pages.AuthLogin.AuthLoginScreen
import com.project.freshtomatoes.ui.pages.AuthSignUp.AuthSignUpScreen
import com.project.freshtomatoes.ui.pages.GenrePage.Genre
import com.project.freshtomatoes.ui.pages.Home.Home
import com.project.freshtomatoes.ui.pages.Info.Information
import com.project.freshtomatoes.ui.pages.MovieDetails.MovieDetails
import com.project.freshtomatoes.ui.pages.MovieReviews.MovieReviews
import com.project.freshtomatoes.ui.pages.Profile.Profile
import com.project.freshtomatoes.ui.pages.Review.Review
import com.project.freshtomatoes.ui.pages.YourReviews.YourReviews

/**
 * Router is a sealed class to "statically" represent our routes.
 */
sealed class Router(val route: String) {
    object Home : Router("Home")
    object About : Router("About")
    object YourReviews : Router("Your Reviews")
    object MovieReviews : Router("MovieReviews/{id}/{name}") {
        fun go(id: Int, name: String) = "MovieReviews/$id/$name"
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
    object Info : Router("Info")

    object GenreMovieList : Router(route = "Genre/{id}")
    {
        fun go(id: Int) = "Review/$id"
    }
}

/**
 * Navigation graph for the FreshTomatoes application. Contains all routes defined
 * in Router class.
 */
@Composable
fun NavGraph() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "Home") {
        composable(Router.Home.route) { Home() }
        composable(Router.About.route) { AboutUs() }
        composable(Router.YourReviews.route) { YourReviews() }
        composable(Router.MovieReviews.route) { MovieReviews(movieId = it.arguments?.getString("id")?.toInt() ?: -1, name = it.arguments?.getString("name")) }
        composable(Router.MovieDetails.route) { MovieDetails(id = it.arguments?.getString("index")?.toInt() ?: -1) }
        composable(Router.GenreMovieList.route) { Genre(id = it.arguments?.getString("id")?.toInt() ?: -1) }
        composable(Router.Account.route) { AuthLoginScreen() }
        composable(Router.SignUp.route) { AuthSignUpScreen() }
        composable(Router.Review.route) { Review(id = it.arguments?.getString("index")?.toInt() ?: -1) }
        composable(Router.Info.route){Information()}
        composable(Router.Profile.route) { Profile() }
        composable(
            "deeplink2?id={id}",
            // Note that this navDeepLink pattern has no relation to the route itself
            deepLinks = listOf(navDeepLink { uriPattern = "example://compose.deeplink2/?id={id}" })
        ) { backStackEntry ->
            DeepScreen(backStackEntry.arguments?.getString("id"))
        }
    }
}
