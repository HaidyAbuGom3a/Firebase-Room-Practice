package com.example.firestorepractice.data.mapper

import com.example.firestorepractice.data.model.RoomDto
import com.example.firestorepractice.data.model.UserDto
import com.example.firestorepractice.domain.entity.RoomEntity
import com.example.firestorepractice.domain.entity.UserEntity

fun UserDto.toUserDomain() = UserEntity(
    name = name,
    id = id,
    email = email
)

fun RoomDto.toRoomDomain() = RoomEntity(
    roomId = roomId,
    adminId = adminId,
    users = users?.map { it.toUserDomain() }
)