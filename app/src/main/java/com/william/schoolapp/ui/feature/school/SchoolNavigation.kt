package com.william.schoolapp.ui.feature.school

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class SchoolNavigation(val route: String) {
    object School : SchoolNavigation("school")
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.schoolGraph(navController: NavController) {
    composable(SchoolNavigation.School.route) {
        SchoolScreen()
    }
}