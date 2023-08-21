package com.example.firestorepractice.domain.source

import com.example.firestorepractice.data.model.RoomDto
import com.example.firestorepractice.data.model.UserDto
import com.google.firebase.auth.FirebaseUser

interface FirebaseDataSource {
    suspend fun signUp(email: String, password: String): FirebaseUser?
    suspend fun login(email: String, password: String): FirebaseUser?
    suspend fun addUser(name: String): Boolean
    suspend fun createRoom(adminId: String): RoomDto?
    suspend fun joinRoom(roomId: String, userId: String): RoomDto?
    suspend fun getUser(userId: String): UserDto?
    suspend fun getRoom(roomId: String): RoomDto?
}