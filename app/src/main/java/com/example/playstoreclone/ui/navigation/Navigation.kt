package com.example.playstoreclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playstoreclone.ui.screen.AppDetailScreen
import com.example.playstoreclone.ui.screen.HomeScreen

sealed class Route(val route: String) {
    object Home : Route("home")
    object AppDetail : Route("app_detail/{appId}") {
        fun createRoute(appId: String) = "app_detail/$appId"
    }
}

@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ) {
        composable(Route.Home.route) {
            HomeScreen(
                onAppClick = { appId ->
                    navController.navigate(Route.AppDetail.createRoute(appId))
                }
            )
        }

        composable(Route.AppDetail.route) { backStackEntry ->
            val appId = backStackEntry.arguments?.getString("appId") ?: return@composable
            AppDetailScreen(
                appId = appId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

