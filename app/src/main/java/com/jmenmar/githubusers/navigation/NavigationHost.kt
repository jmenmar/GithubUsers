package com.jmenmar.githubusers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jmenmar.githubusers.ui.screens.detail.DetailScreen
import com.jmenmar.githubusers.ui.screens.home.HomeScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = "ROOT",
        startDestination = NavigationRoute.Home.route
    ) {
        composable(route = NavigationRoute.Home.route) {
            HomeScreen(
                onDetail = { username ->
                    if (username.isNotEmpty()) {
                        navController.navigate(NavigationRoute.Detail.where(username = username))
                    }
                }
            )
        }

        composable(route = NavigationRoute.Detail.route) {
            DetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}