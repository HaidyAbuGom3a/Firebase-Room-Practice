package com.example.firestorepractice.presentation.screens.room

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestorepractice.domain.usecase.GetRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getRoomUseCase: GetRoomUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(RoomUiState())
    val state = _state.asStateFlow()
    private val args = RoomArgs(savedStateHandle)

    init {
        getRoomData()
    }

    private fun getRoomData() {
        viewModelScope.launch {
            val room = getRoomUseCase(args.roomId)?.toRoomUiState()
            if (room != null) {
                if (room.adminId == args.userId) {
                    _state.update {
                        it.copy(
                            roomId = room.roomId,
                            users = room.users,
                            adminId = room.adminId,
                            visible = true
                        )
                    }
                } else {
                    _state.update { room }
                }

            } else {
                Log.e("haidy", "no room found")
            }
        }
    }

}