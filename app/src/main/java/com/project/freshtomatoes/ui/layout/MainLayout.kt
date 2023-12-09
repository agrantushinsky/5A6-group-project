package com.project.freshtomatoes.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

//Coded by Nitpreet
/**
 * MainLayout for the application using Scaffold. Uses TopBar and BottomBar.
 *
 * @param modifier Modifier parameter. Defaulted to Modifier.
 * @param content The composable function to be displayed as content within the Scaffold.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) {
        Column(modifier = Modifier.padding(it).fillMaxHeight()) {
            content()
        }
    }
}
