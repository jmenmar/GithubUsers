package com.jmenmar.githubusers.ui.screens.detail

import com.jmenmar.githubusers.domain.model.Repository
import com.jmenmar.githubusers.domain.model.User

data class DetailState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val followers: List<User>? = emptyList(),
    val repos: List<Repository>? = emptyList(),
    val followersError: String? = null,
    val reposError: String? = null
)
