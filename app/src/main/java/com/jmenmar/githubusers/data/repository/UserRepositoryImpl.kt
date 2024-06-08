package com.jmenmar.githubusers.data.repository

import com.jmenmar.githubusers.data.local.dao.UserDao
import com.jmenmar.githubusers.data.mapper.toDomain
import com.jmenmar.githubusers.data.mapper.toEntity
import com.jmenmar.githubusers.data.remote.network.GithubService
import com.jmenmar.githubusers.data.remote.utils.resultOf
import com.jmenmar.githubusers.domain.model.Repository
import com.jmenmar.githubusers.domain.model.User
import com.jmenmar.githubusers.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: GithubService,
    private val userDao: UserDao
): UserRepository {
    override suspend fun getUsers(query: String?): Flow<List<User>> {
        val roomFlow = userDao.getUsers(query = query).map { list -> list.map { it.toDomain() } }
        val apiFlow = getUsersFromApi(query = query)

        return roomFlow.combine(apiFlow) { list, _ ->
            list
        }
    }

    override suspend fun getUser(username: String): User? {
        return api.getUser(username = username)?.toDomain()
    }

    override suspend fun getFollowers(username: String): List<User> {
        return api.getFollowers(username = username).map {
            it.toDomain()
        }
    }

    override suspend fun getRepositories(username: String): List<Repository> {
        return api.getRepos(username = username).map {
            it.toDomain()
        }
    }

    override suspend fun insertUsers(users: List<User>) {
        userDao.insertUsers(users = users.map { it.toEntity() })
    }

    private fun getUsersFromApi(query: String?): Flow<List<User>> {
        return flow {
            resultOf {
                val result = withContext(Dispatchers.IO) {
                    val users = if (query.isNullOrEmpty()) {
                        api.getUsers().map { it.toDomain() }
                    } else {
                        api.getUsersBy(query).map { it.toDomain() }
                    }
                    userDao.insertUsers(users.map { it.toEntity() })
                    users
                }
                result
            }
            emit(emptyList<User>())
        }.onStart {
            emit(emptyList())
        }
    }
}