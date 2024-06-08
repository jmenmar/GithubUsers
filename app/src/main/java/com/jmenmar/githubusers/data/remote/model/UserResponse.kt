package com.jmenmar.githubusers.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: Int? = null,
    @SerializedName("login")
    val username: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    val name: String? = null
)
