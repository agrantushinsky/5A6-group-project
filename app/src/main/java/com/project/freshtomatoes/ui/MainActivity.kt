package com.project.freshtomatoes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.project.freshtomatoes.ui.NavGraph
import com.project.freshtomatoes.ui.layout.MainLayout
import com.project.freshtomatoes.ui.pages.DeepScreen

val LocalNavController = compositionLocalOf<NavHostController> { error("No Nav Controller") }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(
                useDarkTheme = true
            ) {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    MainLayout {
                        NavGraph()
                    }
                }
            }
        }
    }
}
