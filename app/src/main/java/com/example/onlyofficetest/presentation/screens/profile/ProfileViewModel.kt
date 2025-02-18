package com.example.onlyofficetest.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlyofficetest.data.local.DataStoreManager
import com.example.onlyofficetest.data.repository.AuthRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ProfileViewModel(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val repository = AuthRepository()
    private var errorMessage by mutableStateOf("")
    var state by mutableStateOf(ProfileState())
        private set

    fun logoutUser(onSuccess: () -> Unit) {

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val response: Response<Unit> = repository.logout()
                if (response.isSuccessful) {
                    dataStoreManager.clearAuthData()
                    onSuccess()
                } else {
                    errorMessage = "Logout failed: please try again later"
                }
            } catch (e: Exception) {
                errorMessage = if (e is IOException) {
                    "Network Error: please check you connection"
                } else {
                    "An error occurred: please try again later"
                }
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }
}