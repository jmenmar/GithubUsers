package com.jmenmar.githubusers.domain.usecase

import com.jmenmar.githubusers.domain.model.User
import com.jmenmar.githubusers.domain.repository.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(username: String): User? {
        return userRepository.getUser(username = username)
    }
}