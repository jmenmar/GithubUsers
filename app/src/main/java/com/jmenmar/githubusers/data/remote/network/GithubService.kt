package com.jmenmar.githubusers.data.remote.network

import com.jmenmar.githubusers.data.remote.model.ReposResponse
import com.jmenmar.githubusers.data.remote.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubService @Inject constructor(
    private val api: GithubClient
) {
    suspend fun getUsers(): List<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.getUsers()
        }
    }

    suspend fun getUsersBy(query: String): List<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.getUsersBy(query = query).items
        }
    }

    suspend fun getUser(username: String): UserResponse? {
        return withContext(Dispatchers.IO) {
            api.getUser(username = username)
        }
    }

    suspend fun getFollowers(username: String): List<UserResponse> {
        return withContext(Dispatchers.IO) {
            api.getFollowers(username = username)
        }
    }

    suspend fun getRepos(username: String): List<ReposResponse> {
        return withContext(Dispatchers.IO) {
            api.getRepos(username = username)
        }
    }
}