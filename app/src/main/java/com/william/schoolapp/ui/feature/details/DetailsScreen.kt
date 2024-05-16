package com.william.schoolapp.ui.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.william.schoolapp.ui.feature.school.SchoolViewModel
import com.william.schoolapp.ui.utils.rememberStateWithLifecycle

@Composable
fun DetailsScreenRoute(navController: NavHostController) {
    val viewModel = hiltViewModel<SchoolViewModel>()
    val viewState by rememberStateWithLifecycle(viewModel.viewState)
}
