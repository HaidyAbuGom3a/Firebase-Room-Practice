package com.example.firestorepractice.presentation.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestorepractice.domain.usecase.SignUpUserUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpUiState())
    val state = _state.asStateFlow()

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        _state.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun onClickPasswordEyeIcon() {
        _state.update { it.copy(passwordVisibility = !it.passwordVisibility) }
    }

    fun onClickConfirmPasswordEyeIcon() {
        _state.update { it.copy(confirmPasswordVisibility = !it.confirmPasswordVisibility) }
    }

    fun onClickSignUp(
        email: String,
        password: String,
        confirmPassword: String,
        callback: (ResponseType, FirebaseUser?) -> Unit
    ) {
        if (password == confirmPassword) {
            viewModelScope.launch {
                val result = signUpUserUseCase(email, password)
                val response = if (result != null) ResponseType.SUCCESS else ResponseType.FAILED
                _state.update { it.copy(response = response) }
                callback(response, result)
            }
        } else {
            callback(ResponseType.PASSWORD_NOT_MATCH, null)
        }

    }
}