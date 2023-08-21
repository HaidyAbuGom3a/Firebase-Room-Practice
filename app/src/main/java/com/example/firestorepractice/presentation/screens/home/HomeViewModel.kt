package com.example.firestorepractice.presentation.screens.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestorepractice.domain.usecase.CreateRoomUseCase
import com.example.firestorepractice.domain.usecase.GetUserUseCase
import com.example.firestorepractice.domain.usecase.JoinRoomUseCase
import com.example.firestorepractice.presentation.screens.profile.UserUiState
import com.example.firestorepractice.presentation.screens.room.toRoomUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.security.auth.callback.Callback

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val createRoomUseCase: CreateRoomUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(UserUiState())
    val state = _state.asStateFlow()

    private val args = HomeArgs(savedStateHandle)

    init{
        getUserData()
    }

    private fun getUserData( )
    {
        viewModelScope.launch {
            val response = getUserUseCase(args.id)
            if(response != null) {
                _state.update { response.toUserUiState() }
            }else{
                Log.e("haidy","no response")
            }
        }
    }

    fun onClickCreateRoom(
        callback: (Boolean,String?) -> Unit
    ){
        viewModelScope.launch {
            val response = createRoomUseCase(args.id)?.toRoomUiState()
            callback(response != null, response?.roomId)
        }
    }
}