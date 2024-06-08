package com.jmenmar.githubusers.domain.model

data class User(
    val id: Int? = null,
    val username: String? = null,
    val avatarUrl: String? = null,
    val name: String? = null,
    var followers: List<User>? = emptyList(),
    var repos: List<Repository>? = emptyList()
)
