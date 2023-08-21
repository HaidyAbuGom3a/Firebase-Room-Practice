package com.example.firestorepractice.domain.usecase

import com.example.firestorepractice.domain.repository.Repository
import javax.inject.Inject

class JoinRoomUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(roomId: String, userId: String) =
        repository.joinRoom(roomId, userId)
}