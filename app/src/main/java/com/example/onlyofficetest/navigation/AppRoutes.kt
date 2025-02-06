package com.example.onlyofficetest.navigation

sealed class AppRoutes(val route: String) {
    data object SplashScreen : AppRoutes("splash_screen")
    data object AuthScreen : AppRoutes("auth_screen")
    data object MainScreen : AppRoutes("main_screen")
}