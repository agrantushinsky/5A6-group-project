package com.project.freshtomatoes.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.em

@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun TopTomatoBar() {

        TopAppBar(title = { Text(text = "FreshTomatos", fontSize = 7.em, fontStyle = FontStyle.Italic) }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),)





}