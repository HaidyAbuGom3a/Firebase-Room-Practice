package com.example.firestorepractice.presentation.screens.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
)