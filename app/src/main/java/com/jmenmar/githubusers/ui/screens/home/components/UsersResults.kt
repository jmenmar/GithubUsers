package com.jmenmar.githubusers.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmenmar.githubusers.domain.model.User

@Composable
fun UsersResults(
    isLoading: Boolean,
    users : List<User>?,
    onDetail: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (!isLoading) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Adaptive(200.dp),
                contentPadding = PaddingValues(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                content = {
                    items(users.orEmpty()) { user ->
                        UserCard(
                            user = user,
                            onClick = { username ->
                                onDetail(username)
                            }
                        )
                    }

                }
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserResultsPreview() {
    UsersResults(
        isLoading = true,
        users = listOf(User(username = "abc")),
        onDetail = {}
    )
}