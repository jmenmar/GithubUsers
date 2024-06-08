package com.jmenmar.githubusers.domain.usecase

import com.jmenmar.githubusers.domain.model.User
import com.jmenmar.githubusers.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetFollowersUseCaseTest {
    @RelaxedMockK
    private lateinit var userRepository: UserRepository
    private lateinit var getFollowersUseCase: GetFollowersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getFollowersUseCase = GetFollowersUseCase(userRepository)
    }

    @Test
    fun `GIVEN null username WHEN getting followers THEN return username null or empty exception`() = runBlocking {
        // GIVEN
        val username: String? = null

        // WHEN
        val result = getFollowersUseCase.invoke(username = username)

        // THEN
        Assert.assertEquals(
            Result.failure<Exception>(Exception("Username is null or empty")).toString(),
            result.toString()
        )
    }

    @Test
    fun `GIVEN an empty username WHEN getting followers THEN return username null or empty exception`() = runBlocking {
        // GIVEN
        val username = ""

        // WHEN
        val result = getFollowersUseCase.invoke(username = username)

        // THEN
        Assert.assertEquals(
            Result.failure<Exception>(Exception("Username is null or empty")).toString(),
            result.toString()
        )
    }

    @Test
    fun `GIVEN a valid username WHEN getting followers THEN return username null or empty exception`() = runBlocking {
        // GIVEN
        val username = "username"

        // WHEN
        val result = getFollowersUseCase.invoke(username = username)

        // THEN
        val followers: List<User> = listOf()
        Assert.assertEquals(
            Result.success(followers).toString(),
            result.toString()
        )
    }
}