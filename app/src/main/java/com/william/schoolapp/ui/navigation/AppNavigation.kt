package com.william.schoolapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.william.schoolapp.ui.feature.school.SchoolScreenRoute

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.School.route) {
        composable(Screen.School.route) {
            SchoolScreenRoute(navController)
        }
    }
}