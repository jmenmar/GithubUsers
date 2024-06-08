package com.jmenmar.githubusers.data.remote.network

import com.jmenmar.githubusers.data.remote.model.ReposResponse
import com.jmenmar.githubusers.data.remote.model.UserQueryResponse
import com.jmenmar.githubusers.data.remote.model.UserResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubClient {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("search/users")
    suspend fun getUsersBy(@Query("q") query: String): UserQueryResponse

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): UserResponse?

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): List<UserResponse>

    @GET("users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String): List<ReposResponse>
}