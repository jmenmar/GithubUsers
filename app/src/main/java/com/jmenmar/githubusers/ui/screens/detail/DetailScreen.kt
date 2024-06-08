package com.jmenmar.githubusers.ui.screens.detail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.jmenmar.githubusers.R
import com.jmenmar.githubusers.ui.screens.detail.components.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = state.user?.username.orEmpty())
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Outlined.ArrowBack, stringResource(id = R.string.return_to_home))
                    }
                }
            )
        },
        content = { paddingValues ->
            DetailContent(
                paddingValues = paddingValues,
                user = state.user
            )
        }
    )
}

@Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen(onBack = {})
}