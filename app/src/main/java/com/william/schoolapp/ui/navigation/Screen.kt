package com.william.schoolapp.ui.navigation

sealed class Screen(val route: String) {
    object School: Screen("school")
}