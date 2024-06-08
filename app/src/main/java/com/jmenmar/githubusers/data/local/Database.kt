package com.jmenmar.githubusers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmenmar.githubusers.data.local.dao.UserDao
import com.jmenmar.githubusers.data.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubUsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}