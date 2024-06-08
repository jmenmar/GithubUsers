package com.jmenmar.githubusers.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserQueryResponse(
    @SerializedName("total_count")
    val total: Int,
    val items: List<UserResponse>
)