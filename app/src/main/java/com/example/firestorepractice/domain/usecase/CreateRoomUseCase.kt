package com.example.firestorepractice.domain.usecase

import com.example.firestorepractice.domain.repository.Repository
import javax.inject.Inject

class CreateRoomUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(adminId: String) =
        repository.createRoom(adminId)
}