package com.jmenmar.githubusers.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.githubusers.domain.usecase.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userUseCases: UserUseCases
): ViewModel() {
    var state by mutableStateOf(DetailState())
        private set

    init {
        savedStateHandle.get<String>("username")?.let { username ->
            getUser(username = username)
        }
    }

    private fun getUser(username: String) {
        state = state.copy(isLoading = true, followersError = null)
        viewModelScope.launch {
            val user = userUseCases.getUserUseCase(username = username)
            state = state.copy(user = user)
            if (user != null) {
                userUseCases.getFollowersUseCase(username = username)
                    .onSuccess { followers ->
                        user.followers = followers
                        state = state.copy(user = user)
                    }.onFailure {
                        state = state.copy(followersError = it.message)
                    }
                userUseCases.getRepositoriesUseCase(username = username)
                    .onSuccess { repos ->
                        user.repos = repos
                        state = state.copy(user = user)
                    }.onFailure {
                        state = state.copy(reposError = it.message)
                    }
            }
            state = state.copy(isLoading = false)
        }
    }
}