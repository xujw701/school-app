package com.william.schoolapp.ui.feature.school

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class SchoolNavigation(val route: String) {
    object School : SchoolNavigation("school")
}

fun NavGraphBuilder.schoolGraph(navController: NavController) {
    composable(SchoolNavigation.School.route) {
        SchoolScreen()
    }
}