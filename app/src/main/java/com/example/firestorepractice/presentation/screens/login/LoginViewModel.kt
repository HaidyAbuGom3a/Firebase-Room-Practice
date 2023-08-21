package com.example.firestorepractice.presentation.screens.login

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestorepractice.domain.usecase.LoginUserUseCase
import com.example.firestorepractice.presentation.screens.signup.ResponseType
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    fun onEmailChanged(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onClickPasswordEyeIcon() {
        _state.update { it.copy(passwordVisibility = !it.passwordVisibility) }
    }


    fun onClickLogin(
        email: String,
        password: String,
        callback: (FirebaseUser?) -> Unit
    ) {
        viewModelScope.launch {
            val result = loginUserUseCase(email, password)
            callback(result)
        }

    }
}