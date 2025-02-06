package com.example.onlyofficetest.presentation.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.onlyofficetest.core.GlobalConfig
import com.example.onlyofficetest.core.RetrofitClient
import com.example.onlyofficetest.data.local.DataStoreManager
import com.example.onlyofficetest.navigation.AppRoutes

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    dataStoreManager: DataStoreManager,
    navController: NavController
) {
    val token by dataStoreManager.tokenFlow.collectAsState(initial = null)
    val savedUrl by dataStoreManager.baseUrl.collectAsState(initial = null)

    LaunchedEffect(token, savedUrl) {
        // Если сохранён URL не пустой, обновляем глобальную конфигурацию и RetrofitClient
        if (!savedUrl.isNullOrEmpty()) {
            GlobalConfig.baseUrl = savedUrl.orEmpty()
            RetrofitClient.updateBaseUrl(savedUrl.orEmpty())
        }
        // Навигация в зависимости от наличия токена
        if (token.isNullOrEmpty()) {
            navController.navigate(AppRoutes.AuthScreen.route) {
                popUpTo(AppRoutes.SplashScreen.route) { inclusive = true }
            }
        } else {
            navController.navigate(AppRoutes.MainScreen.route) {
                popUpTo(AppRoutes.SplashScreen.route) { inclusive = true }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}