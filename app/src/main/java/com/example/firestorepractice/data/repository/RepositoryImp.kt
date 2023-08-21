package com.example.firestorepractice.data.repository

import com.example.firestorepractice.data.mapper.toRoomDomain
import com.example.firestorepractice.data.mapper.toUserDomain
import com.example.firestorepractice.domain.entity.RoomEntity
import com.example.firestorepractice.domain.entity.UserEntity
import com.example.firestorepractice.domain.repository.Repository
import com.example.firestorepractice.domain.source.FirebaseDataSource
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) : Repository {
    override suspend fun addUser(name: String): Boolean =
        firebaseDataSource.addUser(name)

    override suspend fun createRoom(adminId: String): RoomEntity? =
        firebaseDataSource.createRoom(adminId)?.toRoomDomain()

    override suspend fun joinRoom(roomId: String, userId: String): RoomEntity? =
        firebaseDataSource.joinRoom(roomId, userId)?.toRoomDomain()

    override suspend fun getUser(userId: String): UserEntity? =
        firebaseDataSource.getUser(userId)?.toUserDomain()

    override suspend fun getRoom(roomId: String): RoomEntity? =
        firebaseDataSource.getRoom(roomId)?.toRoomDomain()
}