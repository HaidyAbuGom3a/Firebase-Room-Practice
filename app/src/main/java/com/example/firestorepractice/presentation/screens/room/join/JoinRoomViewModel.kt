package com.example.firestorepractice.presentation.screens.room.join

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestorepractice.domain.usecase.JoinRoomUseCase
import com.example.firestorepractice.presentation.screens.room.toRoomUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinRoomViewModel @Inject constructor(
    private val joinRoomUseCase: JoinRoomUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()
    private val args = JoinRoomArgs(savedStateHandle)

    fun onIdChanged(roomId: String){
        _state.update { roomId }
    }
    fun onClickJoin(callback: (Boolean,String) -> Unit){
        viewModelScope.launch {
            val response = joinRoomUseCase(_state.value,args.id)?.toRoomUiState()
            callback(response != null, args.id)
        }
    }

}