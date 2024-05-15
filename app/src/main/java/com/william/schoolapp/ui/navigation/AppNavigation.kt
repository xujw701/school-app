package com.william.schoolapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.william.schoolapp.ui.feature.school.schoolGraph

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.School.route) {
        schoolGraph(navController)
    }
}