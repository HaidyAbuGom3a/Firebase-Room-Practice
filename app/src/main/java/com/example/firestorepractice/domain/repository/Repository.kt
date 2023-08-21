package com.example.firestorepractice.domain.repository

import com.example.firestorepractice.domain.entity.RoomEntity
import com.example.firestorepractice.domain.entity.UserEntity

interface Repository {
    suspend fun addUser(name: String): Boolean
    suspend fun createRoom(adminId: String): RoomEntity?
    suspend fun joinRoom(roomId: String, userId: String): RoomEntity?
    suspend fun getUser(userId: String): UserEntity?
    suspend fun getRoom(roomId: String): RoomEntity?
}