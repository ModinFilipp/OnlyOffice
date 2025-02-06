package com.example.onlyofficetest.presentation.screens.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.onlyofficetest.R
import com.example.onlyofficetest.presentation.composeComponents.PlaceholderScreen
import com.example.onlyofficetest.presentation.screens.documents.DocumentsScreen
import com.example.onlyofficetest.presentation.screens.profile.ProfileScreen
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val innerNavController = rememberNavController()

    val items = listOf(
        MainScreenRoutes.Documents,
        MainScreenRoutes.Rooms,
        MainScreenRoutes.Trash,
        MainScreenRoutes.Profile
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.navigationBarsPadding(),
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                val currentBackStackEntry by innerNavController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination?.route

                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                modifier = Modifier.size(15.dp),
                                painter = when (screen) {
                                    MainScreenRoutes.Documents -> if (currentDestination == screen.route)
                                        painterResource(R.drawable.circle) else painterResource(R.drawable.triangle)

                                    MainScreenRoutes.Rooms -> if (currentDestination == screen.route)
                                        painterResource(R.drawable.circle) else painterResource(R.drawable.triangle)

                                    MainScreenRoutes.Trash -> if (currentDestination == screen.route)
                                        painterResource(R.drawable.circle) else painterResource(R.drawable.triangle)

                                    MainScreenRoutes.Profile -> painterResource(R.drawable.profile)
                                },
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = screen.route.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        selected = currentDestination == screen.route,
                        onClick = {
                            innerNavController.navigate(screen.route) {
                                popUpTo(innerNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = innerNavController,
            startDestination = MainScreenRoutes.Documents.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(MainScreenRoutes.Documents.route) { DocumentsScreen() }
            composable(MainScreenRoutes.Rooms.route) { PlaceholderScreen() }
            composable(MainScreenRoutes.Trash.route) { PlaceholderScreen() }
            composable(MainScreenRoutes.Profile.route) { ProfileScreen(navController = navController) }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview(modifier: Modifier = Modifier) {
    OnlyOfficeTestTheme {
        MainScreen(navController = rememberNavController())
    }
}