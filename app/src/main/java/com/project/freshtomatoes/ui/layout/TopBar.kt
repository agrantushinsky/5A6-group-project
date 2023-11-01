package com.project.freshtomatoes.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.project.freshtomatoes.LocalNavController
import com.project.freshtomatoes.ui.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTomatoBar() {
    val navController = LocalNavController.current

    TopAppBar(
        title = { Text(text = "FreshTomatoes") },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        actions = {
            IconButton(onClick = { navController.navigate(Router.Account.route) }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account button"
                )
            }
        }
    )
}
