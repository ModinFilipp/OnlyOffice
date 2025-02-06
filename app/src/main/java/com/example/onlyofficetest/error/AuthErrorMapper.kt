package com.example.onlyofficetest.error

class AuthErrorMapper : ErrorMapper {
    override fun mapErrorCode(code: Int): String {
        return when (code) {
            400 -> "User name, password or password hash is empty."
            401 -> "User authentication failed."
            404 -> "The user could not be found."
            429 -> "Too many login attempts. Please try again later."
            else -> "Authentication failed: $code"
        }
    }
}