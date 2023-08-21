package com.example.firestorepractice.presentation.screens.room

import com.example.firestorepractice.domain.entity.RoomEntity
import com.example.firestorepractice.presentation.screens.home.toUserUiState
import com.example.firestorepractice.presentation.screens.profile.UserUiState

data class RoomUiState(
    val roomId: String = "",
    val adminId: String = "",
    val users: List<UserUiState> = emptyList(),
    val visible: Boolean = false
)

fun RoomEntity.toRoomUiState() = RoomUiState(
    roomId = roomId ?: "",
    adminId = adminId ?: "",
    users = users?.map { it.toUserUiState() } ?: emptyList()
)