package com.william.schoolapp.ui.feature.school

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.william.schoolapp.data.model.SchoolRecord
import com.william.schoolapp.ui.utils.rememberStateWithLifecycle

@ExperimentalMaterial3Api
@Composable
internal fun SchoolScreen() {
    val viewModel = hiltViewModel<SchoolViewModel>()
    val viewState by rememberStateWithLifecycle(viewModel.viewState)

    if (viewState.schoolList.isNotEmpty()) {
        SchoolScreenContent(viewState.schoolList)
    } else {
        LoadingIndicator()
    }
}

@Composable
fun SchoolScreenContent(schools: List<SchoolRecord>) {
    LazyColumn {
        items(schools) { item ->
            SchoolItem(school = item)
        }
    }
}

@Composable
fun SchoolItem(school: SchoolRecord) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "School ID: ${school.schoolId}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Name: ${school.orgName}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Telephone: ${school.telephone}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Email: ${school.email}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Website: ${school.url}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Type: ${school.orgType}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Status: ${school.status}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

val sampleSchools = listOf(
    SchoolRecord(
        "1",
        "Sample School 1",
        "1234567890",
        "email1@school.org",
        "http://school1.org",
        "Public",
        "Active"
    ),
    SchoolRecord(
        "2",
        "Sample School 2",
        "0987654321",
        "email2@school.org",
        "http://school2.org",
        "Private",
        "Active"
    ),
    // Add more sample records if needed
)

@Preview(showBackground = true)
@Composable
fun PreviewSchoolScreen() {
    SchoolScreenContent(schools = sampleSchools)
}