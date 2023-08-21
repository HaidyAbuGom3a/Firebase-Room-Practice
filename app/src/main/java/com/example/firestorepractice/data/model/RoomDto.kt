package com.example.firestorepractice.data.model

data class RoomDto(
    val roomId: String? = "",
    val adminId: String? = "",
    val users: List<UserDto>? = emptyList()
)