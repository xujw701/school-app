package com.william.schoolapp.ui.feature.school

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.william.schoolapp.ui.theme.SchoolAppTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun SchoolScreen() {
    val viewModel = hiltViewModel<SchoolViewModel>()
    Greeting("Here you go!")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SchoolAppTheme {
        Greeting("Here you go!")
    }
}