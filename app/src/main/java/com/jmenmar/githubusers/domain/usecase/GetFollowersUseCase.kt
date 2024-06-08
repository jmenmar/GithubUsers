package com.jmenmar.githubusers.domain.usecase

import com.jmenmar.githubusers.domain.model.User
import com.jmenmar.githubusers.domain.repository.UserRepository

class GetFollowersUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String?): Result<List<User>> {
        return if (username.isNullOrEmpty()) {
            Result.failure(Exception("Username is null or empty"))
        } else {
            val followers = userRepository.getFollowers(username = username)
            Result.success(followers)
        }
    }
}