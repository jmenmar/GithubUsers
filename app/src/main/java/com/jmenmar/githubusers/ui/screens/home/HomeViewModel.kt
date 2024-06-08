package com.jmenmar.githubusers.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmenmar.githubusers.domain.model.User
import com.jmenmar.githubusers.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {
    private val _users = MutableStateFlow<List<User>?>(null)
    var users = _users.asStateFlow()

    var state by mutableStateOf(HomeState())
        private set

    private var currentJob: Job? = null

    init {
        getUsers()
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.ChangeQuery -> {
                state = state.copy(query = event.query)
                getUsers()
            }
            HomeEvent.ClearQuery -> {
                state = state.copy(query = "")
                getUsers()
            }
        }
    }

    private fun getUsers() {
        state = state.copy(isLoading = true)
        currentJob?.cancel()
        currentJob = viewModelScope.launch {
            getUsersUseCase(query = state.query).collectLatest {
                _users.value = it
                state = state.copy(isLoading = false)
            }
        }
    }
}