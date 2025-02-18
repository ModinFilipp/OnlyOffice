package com.example.onlyofficetest.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onlyofficetest.R
import com.example.onlyofficetest.data.local.DataStoreManager
import com.example.onlyofficetest.navigation.AppRoutes
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme
import org.koin.androidx.compose.getViewModel
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ProfileViewModel = getViewModel(),
    dataStoreManager: DataStoreManager = koinInject()
) {
    val email by dataStoreManager.emailFlow.collectAsState("")
    val state by remember { derivedStateOf { viewModel.state } }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        ProfileContent(
            modifier = modifier,
            userName = email ?: "",
            onLogoutClick = {
                viewModel.logoutUser {
                    navController.navigate(AppRoutes.AuthScreen.route) {
                        popUpTo(AppRoutes.MainScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }
        )
        // Индикатор загрузки
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x88000000)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    userName: String,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 10.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 70.dp),
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(100.dp)
                    .background(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        shape = CircleShape
                    ),
                painter = painterResource(R.drawable.avatar),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 40.dp),
                text = "User Name",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.padding(top = 40.dp),
                text = "E-mail"
            )
            Text(
                text = userName
            )
            Button(
                modifier = Modifier
                    .padding(top = 50.dp),
                onClick = { onLogoutClick() }
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = "Logout"
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProfileContentPreview(modifier: Modifier = Modifier) {
    OnlyOfficeTestTheme {
        ProfileContent(
            onLogoutClick = {},
            userName = "ookk"
        )
    }
}