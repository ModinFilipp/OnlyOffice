package com.example.onlyofficetest.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApiService {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("/api/2.0/authentication")
    suspend fun authenticate(@Body request: AuthRequest): Response<AuthResponseWrapper>

    @POST("/api/2.0/authentication/logout")
    suspend fun logout(): Response<Unit>

}

data class AuthRequest(
    @SerializedName("UserName") val userName: String,
    @SerializedName("Password") val password: String,
)

data class AuthResponse(
    @SerializedName("token") val token: String?,
    @SerializedName("expires") val expires: String
)

data class AuthResponseWrapper(
    @SerializedName("response") val response: AuthResponse,
    @SerializedName("status") val status: Int,
    @SerializedName("statusCode") val statusCode: Int
)

