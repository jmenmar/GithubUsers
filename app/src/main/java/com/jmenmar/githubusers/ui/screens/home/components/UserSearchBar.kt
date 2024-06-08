package com.jmenmar.githubusers.ui.screens.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jmenmar.githubusers.R
import com.jmenmar.githubusers.ui.screens.home.HomeEvent

@Composable
fun UserSearchBar(
    search: String,
    onEvent: (HomeEvent) -> Unit,
    onValueChange: (String) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = search,
            singleLine = true,
            maxLines = 1,
            placeholder = { Text(text = stringResource(id = R.string.search_user)) },
            onValueChange = { onValueChange(it) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.search_user)
                )
            },
            trailingIcon = {
                if (search.isNotEmpty()) {
                    IconButton(
                        onClick = { onEvent(HomeEvent.ClearQuery) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = stringResource(id = R.string.clear)
                        )
                    }
                }
            }
        )
    }
}