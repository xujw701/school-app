package com.william.schoolapp.ui.feature.school

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.william.schoolapp.R
import com.william.schoolapp.data.model.SchoolRecord
import com.william.schoolapp.ui.feature.school.SchoolViewState.State
import com.william.schoolapp.ui.utils.rememberStateWithLifecycle

@Composable
fun SchoolScreenRoute(navController: NavHostController) {
    val viewModel = hiltViewModel<SchoolViewModel>()
    val viewState by rememberStateWithLifecycle(viewModel.viewState)

    SchoolScreen(
        viewState = viewState,
        loadMore = viewModel::loadMore,
        refresh = viewModel::refresh
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolScreen(
    viewState: SchoolViewState,
    loadMore: () -> Unit,
    refresh: () -> Unit) {
    val pullRefreshState = rememberPullToRefreshState()

    Box(Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)) {
        Column(Modifier.fillMaxSize()) {
            if (!pullRefreshState.isRefreshing) {
                SchoolListView(
                    viewState = viewState,
                    loadMore = loadMore,
                )
            }
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }
}

@Composable
fun SchoolListView(
    viewState: SchoolViewState,
    loadMore: () -> Unit,
) {
    when (viewState.state) {
        State.LOADING -> {
            LoadingIndicator()
        }
        State.ERROR -> {
            Text(
                text = "Error",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        State.EMPTY -> {
            Text(
                text = "No data",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        State.SUCCESS -> {
            LazyColumn {
                items(viewState.schools) { item ->
                    SchoolCard(school = item)
                }
            }
        }
    }
}

@Composable
fun SchoolCard(school: SchoolRecord) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                painter = painterResource(id = getSchoolImage(school.schoolId)),
                contentScale = ContentScale.Crop,
                contentDescription = "",
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = school.orgName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Status: ${school.status}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = school.telephone)
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

private fun getSchoolImage(schoolId: Int): Int {
    return when (schoolId % 5) {
        1 -> R.drawable.school1
        2 -> R.drawable.school2
        3 -> R.drawable.school3
        else -> R.drawable.school4
    }
}

private val mockSchools = listOf(
    SchoolRecord(
        1,
        "Sample School 1",
        "1234567890",
        "email1@school.org",
        "http://school1.org",
        "Public",
        "Active"
    ),
    SchoolRecord(
        2,
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
    val schoolViewState = SchoolViewState(
        state = State.SUCCESS,
        schools = mockSchools
    )
    SchoolScreen(schoolViewState, {}, {})
}