package com.jmenmar.githubusers.data.mapper

import com.jmenmar.githubusers.domain.model.Repository
import com.jmenmar.githubusers.data.remote.model.ReposResponse

fun ReposResponse.toDomain(): Repository {
    return Repository(
        id = this.id,
        name = this.name
    )
}