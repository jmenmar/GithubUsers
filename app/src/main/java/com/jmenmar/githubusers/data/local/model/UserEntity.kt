package com.jmenmar.githubusers.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "login")
    val username: String,
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String,
    val name: String
)