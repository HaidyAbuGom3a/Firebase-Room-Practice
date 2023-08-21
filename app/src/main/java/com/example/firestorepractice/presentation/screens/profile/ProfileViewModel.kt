package com.example.firestorepractice.presentation.screens.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestorepractice.domain.usecase.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(UserUiState())
    val state = _state.asStateFlow()
    private val args = ProfileArgs(savedStateHandle)

    fun onNameChanged(name: String) {
        _state.update { it.copy(name = name) }
    }

    fun onClickCreateAccount(
        callback: (Boolean, String) -> Unit
    ) {
        viewModelScope.launch {
            val result = addUserUseCase(name = _state.value.name)
            callback(result, args.id)
        }
    }
}