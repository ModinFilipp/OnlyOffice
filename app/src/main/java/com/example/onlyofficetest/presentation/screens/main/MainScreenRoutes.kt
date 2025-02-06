package com.example.onlyofficetest.presentation.screens.main

sealed class MainScreenRoutes(val route: String) {
    data object Documents : MainScreenRoutes("document")
    data object Rooms : MainScreenRoutes("rooms")
    data object Trash : MainScreenRoutes("trash")
    data object Profile : MainScreenRoutes("profile")
}