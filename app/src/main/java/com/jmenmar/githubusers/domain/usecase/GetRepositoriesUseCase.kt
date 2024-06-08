package com.jmenmar.githubusers.domain.usecase

import com.jmenmar.githubusers.domain.model.Repository
import com.jmenmar.githubusers.domain.repository.UserRepository

class GetRepositoriesUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String?): Result<List<Repository>> {
        return if (username.isNullOrEmpty()) {
            Result.failure(Exception("Username is null or empty"))
        } else {
            val repos = userRepository.getRepositories(username = username)
            Result.success(repos)
        }
    }
}