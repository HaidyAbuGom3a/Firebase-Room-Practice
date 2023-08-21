package com.example.firestorepractice.domain.usecase

import com.example.firestorepractice.domain.repository.Repository
import javax.inject.Inject

class GetRoomUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(id: String) =
        repository.getRoom(id)
}