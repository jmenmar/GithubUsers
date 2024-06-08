package com.jmenmar.githubusers.domain.usecase

import com.jmenmar.githubusers.domain.model.User
import com.jmenmar.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(query: String? = null): Flow<List<User>> {
        return userRepository.getUsers(query = query)
    }
}