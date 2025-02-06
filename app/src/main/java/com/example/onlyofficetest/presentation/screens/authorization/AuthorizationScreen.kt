package com.example.onlyofficetest.presentation.screens.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.onlyofficetest.R
import com.example.onlyofficetest.navigation.AppRoutes
import com.example.onlyofficetest.presentation.composeComponents.LoginInputField
import com.example.onlyofficetest.ui.theme.OnlyOfficeTestTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthorizationViewModel = getViewModel()
) {

    val state by remember { derivedStateOf { viewModel.state } }
    val token by remember { derivedStateOf { viewModel.token } }

    val fieldsIsNotEmpty =
        state.portalText.isNotEmpty()
                && state.emailText.isNotEmpty()
                && state.passwordText.isNotEmpty()

    // Отправляемся на главный экран, если токен есть, иначе на экран регистрации
    LaunchedEffect(token) {
        if (!token.isNullOrEmpty()) {
            navController.navigate(AppRoutes.MainScreen.route) {
                popUpTo(AppRoutes.AuthScreen.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AuthorizationContent(
            modifier = modifier,
            portalText = state.portalText,
            emailText = state.emailText,
            passwordText = state.passwordText,
            fieldsIsNotEmpty = fieldsIsNotEmpty,
            onPortalTextChange = viewModel::updatePortalText,
            onEmailTextChange = viewModel::updateEmailText,
            onPasswordTextChange = viewModel::updatePasswordText,
            onLoginClick = {
                viewModel.loginUser {
                    navController.navigate(AppRoutes.MainScreen.route) {
                        popUpTo(AppRoutes.AuthScreen.route) { inclusive = true }
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
        if (state.errorMessage.isNotEmpty()) {
            Text(
                text = state.errorMessage,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun AuthorizationContent(
    modifier: Modifier = Modifier,
    portalText: String,
    emailText: String,
    passwordText: String,
    fieldsIsNotEmpty: Boolean,
    onPortalTextChange: (String) -> Unit,
    onEmailTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 70.dp),
            text = "Connect to ONLYOFFICE",
            style = MaterialTheme.typography.headlineMedium,
            color = textColor
        )
        LoginInputField(
            value = portalText,
            onValueChange = onPortalTextChange,
            placeHolder = "Portal",
            icon = ImageVector.vectorResource(R.drawable.outline_person_24),
            modifier = Modifier.padding(top = 50.dp)
        )
        LoginInputField(
            value = emailText,
            onValueChange = onEmailTextChange,
            placeHolder = "E-mail",
            icon = ImageVector.vectorResource(R.drawable.outline_person_24),
            modifier = Modifier.padding(top = 20.dp)
        )
        LoginInputField(
            value = passwordText,
            onValueChange = onPasswordTextChange,
            placeHolder = "Password",
            icon = ImageVector.vectorResource(R.drawable.outline_person_24),
            modifier = Modifier.padding(top = 20.dp)
        )
        Button(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            onClick = { onLoginClick() },
            enabled = fieldsIsNotEmpty
        ) {
            Text(
                text = "Login"
            )
        }
    }
}

@Preview
@Composable
private fun AuthorizationContentPreview(modifier: Modifier = Modifier) {
    OnlyOfficeTestTheme {
        var portalText by remember { mutableStateOf("") }
        var emailText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        AuthorizationContent(
            onLoginClick = {},
            portalText = portalText,
            emailText = emailText,
            passwordText = passwordText,
            onPortalTextChange = { portalText = it },
            onEmailTextChange = { emailText = it },
            onPasswordTextChange = { passwordText = it },
            fieldsIsNotEmpty = true
        )
    }
}