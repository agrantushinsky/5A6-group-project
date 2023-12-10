package com.project.freshtomatoes.ui.layout

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlaylistAddCircle
import androidx.compose.material.icons.filled.PsychologyAlt
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.Router

// Coded by Jose
// Represents a NavBarIcon on the bottom bar.
// Contains:
// - route: String => The route the button will use.
// - incon: ImageVector => The ImageVector for the icon displayed.
data class NavBarIcon(
    val route: String,
    val icon: ImageVector
)

// Immutable list of routes to be shown on the bottom bar.
val items = listOf(
    NavBarIcon(route = Router.Home.route, icon = Icons.Filled.Home),
    NavBarIcon(route = Router.YourReviews.route, icon = Icons.Filled.PlaylistAddCircle),
    NavBarIcon(route = Router.About.route, icon = Icons.Filled.PsychologyAlt),
    NavBarIcon(route = Router.Info.route, Icons.Filled.Info)
)

/**
 * BottomBar to be shown at the bottom of the app using Scaffold. Contains
 * all navigation buttons defined in items.
 *
 * @param modifier Modifier parameter. Defaulted to Modifier.
 */
@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(modifier = modifier.statusBarsPadding(), tonalElevation = 0.dp) {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(item.icon, contentDescription = item.route) },
                    selected = currentDestination?.hierarchy?.any {
                        currentDestination.route?.substringBefore('/') ==
                            item.route.substringBefore('/')
                    } == true,
                    onClick = { navController.navigate(item.route) },
                    label = { Text(text = item.route) }
                )
            }
        }
    }
}
