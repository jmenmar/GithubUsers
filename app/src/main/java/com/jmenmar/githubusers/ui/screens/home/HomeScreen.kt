package com.jmenmar.githubusers.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jmenmar.githubusers.ui.screens.home.components.UserSearchBar
import com.jmenmar.githubusers.ui.screens.home.components.UsersResults

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDetail: (String) -> Unit
) {
    val state = viewModel.state
    val users by viewModel.users.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        UserSearchBar(
            search = state.query,
            onEvent = viewModel::onEvent,
            onValueChange = { viewModel.onEvent(HomeEvent.ChangeQuery(it)) }
        )
        UsersResults(
            isLoading = state.isLoading,
            users = users,
            onDetail = onDetail
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onDetail = {})
}