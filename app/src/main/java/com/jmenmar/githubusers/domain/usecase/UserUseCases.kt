package com.jmenmar.githubusers.domain.usecase

data class UserUseCases (
    val getUserUseCase: GetUserUseCase,
    val getFollowersUseCase: GetFollowersUseCase,
    val getRepositoriesUseCase: GetRepositoriesUseCase
)