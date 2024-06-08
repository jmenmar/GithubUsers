package com.jmenmar.githubusers.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavigationRoute(
    val route: String,
    val arguments: List<NamedNavArgument> = listOf()
) {
    data object Home: NavigationRoute(route = "HOME")
    data object Detail : NavigationRoute(
        route = "DETAIL?username={username}",
        arguments = listOf(
            navArgument("username") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) {
        fun where(username: String) = "DETAIL?username=$username"
    }
}