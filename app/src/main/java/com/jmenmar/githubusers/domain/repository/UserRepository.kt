package com.jmenmar.githubusers.domain.repository

import com.jmenmar.githubusers.domain.model.Repository
import com.jmenmar.githubusers.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUsers(query: String? = null): Flow<List<User>>
    suspend fun getUser(username: String): User?
    suspend fun getFollowers(username: String): List<User>
    suspend fun getRepositories(username: String): List<Repository>
    suspend fun insertUsers(users: List<User>)
}