package com.jmenmar.githubusers.data.mapper

import com.jmenmar.githubusers.data.local.model.UserEntity
import com.jmenmar.githubusers.domain.model.User
import com.jmenmar.githubusers.data.remote.model.UserResponse

fun UserResponse.toDomain(): User {
    return User(
        id = this.id,
        username = this.username.orEmpty(),
        avatarUrl = this.avatarUrl.orEmpty(),
        name = this.name
    )
}

fun UserEntity.toDomain(): User {
    return User(
        id = this.id,
        username = this.username.orEmpty(),
        avatarUrl = this.avatarUrl.orEmpty(),
        name = this.name
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = this.id ?: 0,
        username = this.username.orEmpty(),
        avatarUrl = this.avatarUrl.orEmpty(),
        name = this.name.orEmpty()
    )
}