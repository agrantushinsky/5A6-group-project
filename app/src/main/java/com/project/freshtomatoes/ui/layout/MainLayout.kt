package com.project.freshtomatoes.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Scaffold(
        modifier = modifier,
        topBar = { TopTomatoBar() },
        bottomBar = { BottomBar() },
    ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }
}