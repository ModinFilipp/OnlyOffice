package com.example.onlyofficetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.onlyofficetest.data.local.DataStoreManager
import com.example.onlyofficetest.presentation.screens.authorization.AuthorizationScreen
import com.example.onlyofficetest.presentation.screens.main.MainScreen
import com.example.onlyofficetest.presentation.screens.splash.SplashScreen
import org.koin.compose.koinInject

@Composable
fun AppNavigation(
    dataStoreManager: DataStoreManager = koinInject(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route
    ) {
        composable(AppRoutes.SplashScreen.route) {
            SplashScreen(dataStoreManager = dataStoreManager, navController = navController)
        }
        composable(AppRoutes.AuthScreen.route) {
            AuthorizationScreen(navController = navController)
        }
        composable(AppRoutes.MainScreen.route) {
            MainScreen(navController = navController)
        }
    }
}