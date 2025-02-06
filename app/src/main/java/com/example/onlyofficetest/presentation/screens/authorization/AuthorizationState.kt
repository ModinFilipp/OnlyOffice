package com.example.onlyofficetest.presentation.screens.authorization

data class AuthorizationState(
    val portalText: String = "",
    val emailText: String = "",
    val passwordText: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
) {
    private val isPortalValid: Boolean
        get() = portalText.startsWith("https://") && portalText.contains("onlyoffice.com")

    private val isEmailValid: Boolean
        get() = android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()

    private val isPasswordValid: Boolean
        get() = passwordText.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+\$"))

    val isFormValid: Boolean
        get() = portalText.isNotBlank() && emailText.isNotBlank() && passwordText.isNotBlank() &&
                isPortalValid && isEmailValid && isPasswordValid

    val portalError: String?
        get() = when {
            portalText.isBlank() -> "Portal is required"
            !isPortalValid -> "Portal must start with 'https://' and contain 'onlyoffice.com'"
            else -> null
        }

    val emailError: String?
        get() = when {
            emailText.isBlank() -> "Email is required"
            !isEmailValid -> "Invalid email format"
            else -> null
        }

    val passwordError: String?
        get() = when {
            passwordText.isBlank() -> "Password is required"
            !isPasswordValid -> "Password must contain at least one uppercase letter, one lowercase letter and one digit"
            else -> null
        }
}
