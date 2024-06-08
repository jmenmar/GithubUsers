package com.jmenmar.githubusers.ui.screens.detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jmenmar.githubusers.domain.model.User

@Composable
fun DetailContent(
    paddingValues: PaddingValues,
    user: User?
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            UserInfo(user = user)
        }
        item {
            Text(text = "Followers", style = MaterialTheme.typography.titleMedium)
        }
        item {
            UserFollowers(followers = user?.followers.orEmpty())
        }
        item {
            Text(text = "Repositories", style = MaterialTheme.typography.titleMedium)
        }
        userRepositories(repos = user?.repos.orEmpty())
    }
}