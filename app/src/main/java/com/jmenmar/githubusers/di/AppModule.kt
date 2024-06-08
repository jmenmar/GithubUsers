package com.jmenmar.githubusers.di

import android.content.Context
import androidx.room.Room
import com.jmenmar.githubusers.core.Endpoints
import com.jmenmar.githubusers.data.local.GithubUsersDatabase
import com.jmenmar.githubusers.data.local.dao.UserDao
import com.jmenmar.githubusers.data.remote.network.GithubClient
import com.jmenmar.githubusers.data.remote.network.GithubService
import com.jmenmar.githubusers.data.repository.UserRepositoryImpl
import com.jmenmar.githubusers.domain.repository.UserRepository
import com.jmenmar.githubusers.domain.usecase.GetFollowersUseCase
import com.jmenmar.githubusers.domain.usecase.GetRepositoriesUseCase
import com.jmenmar.githubusers.domain.usecase.GetUserUseCase
import com.jmenmar.githubusers.domain.usecase.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiGithub(retrofit: Retrofit): GithubClient {
        return retrofit.create(GithubClient::class.java)
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        githubService: GithubService,
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(api = githubService, userDao = userDao)

    @Provides
    @Singleton
    fun provideUserUseCases(
        repository: UserRepository
    ): UserUseCases {
        return UserUseCases(
            getUserUseCase = GetUserUseCase(repository),
            getFollowersUseCase = GetFollowersUseCase(repository),
            getRepositoriesUseCase = GetRepositoriesUseCase(repository)
        )
    }

    @Singleton
    @Provides
    fun provideUserDao(@ApplicationContext context: Context): UserDao {
        return Room.databaseBuilder(
            context,
            GithubUsersDatabase::class.java,
            "githubusers_db"
        ).build().userDao()
    }
}