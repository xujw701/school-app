package com.william.schoolapp.ui.feature.school

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.william.schoolapp.R
import com.william.schoolapp.ui.feature.school.SchoolViewState.LoadMoreState
import com.william.schoolapp.ui.feature.school.SchoolViewState.State
import com.william.schoolapp.ui.shared.LoadingIndicator
import com.william.schoolapp.ui.theme.DefaultPadding
import com.william.schoolapp.ui.theme.LargeFontSize
import com.william.schoolapp.ui.theme.XSmallPadding
import com.william.schoolapp.ui.theme.XXSmallPadding
import com.william.schoolapp.ui.theme.schoolImageHeight
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
    refresh: suspend () -> Unit) {
    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        LaunchedEffect(true) {
            refresh()
            pullRefreshState.endRefresh()
        }
    }
    Box(Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = DefaultPadding)
                .background(Color.White)
        ) {
            SchoolListView(
                viewState = viewState,
                loadMore = loadMore,
            )
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
                text = stringResource(id = R.string.error),
                fontSize = LargeFontSize,
                fontWeight = FontWeight.Bold
            )
        }
        State.EMPTY -> {
            Text(
                text = stringResource(id = R.string.no_data),
                fontSize = LargeFontSize,
                fontWeight = FontWeight.Bold
            )
        }
        State.SUCCESS -> {
            LazyColumn {
                items(viewState.schools) { item ->
                    SchoolCard(school = item)
                }
                item {
                    LaunchedEffect(true) {
                        if (viewState.loadMoreState == LoadMoreState.SHOW) {
                            loadMore()
                        }
                    }
                }
                item {
                    when (viewState.loadMoreState) {
                        LoadMoreState.LOADING -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = DefaultPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        LoadMoreState.LOAD_ERROR -> {
                            Text(
                                modifier = Modifier
                                    .padding(DefaultPadding)
                                    .fillMaxWidth(),
                                text =  stringResource(id = R.string.failed_to_load_more),
                                fontSize = LargeFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}

@Composable
fun SchoolCard(school: SchoolUio) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(XSmallPadding),
        shape = RoundedCornerShape(XSmallPadding),
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .padding(XSmallPadding),
        ) {
            AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(schoolImageHeight),
                model = school.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(XSmallPadding))
            Text(
                text = school.name,
                fontSize = LargeFontSize,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(XXSmallPadding))
            Text(
                text = school.address,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(XSmallPadding))
            Text(text = "Status: ${school.status}")
            Spacer(modifier = Modifier.height(XXSmallPadding))
            Text(text = school.telephone)
            Spacer(modifier = Modifier.height(XXSmallPadding))
            Text(text = school.email)
        }
    }
}

private val mockSchools = listOf(
    SchoolUio(
        "Sample School 1",
        "Open",
        "1234567890",
        "school@school.com",
        "http://school1.org",
        "7 New town, Auckland",
        "http://school2.org",
    ),
    SchoolUio(
        "Sample School 2",
        "Open",
        "0987654321",
        "school@school.com",
        "http://school2.org",
        "7 New town, Auckland",
        "http://school2.org",
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