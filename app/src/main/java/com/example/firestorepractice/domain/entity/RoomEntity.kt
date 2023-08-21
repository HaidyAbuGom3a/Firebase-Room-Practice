package com.example.firestorepractice.domain.entity

data class RoomEntity(
    val roomId: String? = "",
    val adminId: String? = "",
    val users: List<UserEntity>? = emptyList()
)