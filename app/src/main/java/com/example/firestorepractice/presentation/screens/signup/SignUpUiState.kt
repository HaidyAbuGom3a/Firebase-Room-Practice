package com.example.firestorepractice.presentation.screens.signup

data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisibility: Boolean = false,
    val confirmPasswordVisibility: Boolean = false,
    val response: ResponseType = ResponseType.FAILED
)

enum class ResponseType{
    SUCCESS,
    FAILED,
    PASSWORD_NOT_MATCH
}
