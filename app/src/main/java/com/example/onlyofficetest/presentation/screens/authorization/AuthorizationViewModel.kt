package com.example.onlyofficetest.presentation.screens.authorization

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyofficetest.core.RetrofitClient
import com.example.onlyofficetest.data.local.DataStoreManager
import com.example.onlyofficetest.data.remote.AuthRequest
import com.example.onlyofficetest.data.remote.AuthResponseWrapper
import com.example.onlyofficetest.data.repository.AuthRepository
import com.example.onlyofficetest.error.AuthErrorMapper
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AuthorizationViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val repository = AuthRepository()
    private val errorMapper = AuthErrorMapper()

    var state by mutableStateOf(AuthorizationState())
        private set

    var token by mutableStateOf<String?>(null)

    init {
        checkAuthentication()
    }

    // Проверяем актуальность сессии, если она просрочена - очищаем токен
    private fun checkAuthentication() {
        viewModelScope.launch {
            val (savedToken, savedExpires) = dataStoreManager.getToken()

            if (!savedToken.isNullOrEmpty() && !savedExpires.isNullOrEmpty()) {
                val expiresDateTime =
                    LocalDateTime.parse(savedExpires, DateTimeFormatter.ISO_DATE_TIME)
                if (expiresDateTime.isAfter(LocalDateTime.now())) {
                    token = savedToken
                } else {
                    dataStoreManager.clearAuthData()
                }
            }
        }
    }

    fun updatePortalText(newText: String) {
        state = state.copy(portalText = newText)
    }

    fun updateEmailText(newText: String) {
        state = state.copy(emailText = newText)
    }

    fun updatePasswordText(newText: String) {
        state = state.copy(passwordText = newText)
    }

    // Авторизуемся
    fun loginUser(onSuccess: () -> Unit) {
        if (!state.isFormValid) {
            state = state.copy(
                errorMessage = listOfNotNull(
                    state.portalError,
                    state.emailError,
                    state.passwordError
                ).joinToString("\n")
            )
            return
        }

        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = "")

            try {
                RetrofitClient.updateBaseUrl(state.portalText)

                val request = AuthRequest(
                    userName = state.emailText,
                    password = state.passwordText,
                )

                val response: Response<AuthResponseWrapper> = repository.authenticate(request)
                state = state.copy(isLoading = false)

                if (response.isSuccessful) {
                    val authResponseWrapper = response.body()
                    val authResponse = authResponseWrapper?.response

                    if (authResponse != null && !authResponse.token.isNullOrEmpty()) {
                        token = authResponse.token
                        dataStoreManager.saveAuthData(
                            token = authResponse.token,
                            expires = authResponse.expires,
                            url = state.portalText,
                            email = state.emailText
                        )
                        onSuccess()
                    } else {
                        state = state.copy(errorMessage = "Empty response from server.")
                    }
                } else {
                    state = state.copy(errorMessage = errorMapper.mapErrorCode(response.code()))
                }
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    errorMessage = if (e is IOException) {
                        "Network Error: please check your connection"
                    } else {
                        "An error occurred: please try again later"
                    }
                )
            }
        }
    }
}