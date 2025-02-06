package com.example.onlyofficetest.data.repository

import com.example.onlyofficetest.core.RetrofitClient
import com.example.onlyofficetest.data.remote.AuthRequest
import com.example.onlyofficetest.data.remote.AuthResponseWrapper
import retrofit2.Response

class AuthRepository {

    suspend fun authenticate(authRequest: AuthRequest): Response<AuthResponseWrapper> {
        return RetrofitClient.api.authenticate(authRequest)
    }

    suspend fun logout(): Response<Unit> {
        return RetrofitClient.api.logout()
    }
}