package com.example.firestorepractice.domain.usecase

import com.example.firestorepractice.domain.repository.Repository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(name: String) =
        repository.addUser(name)
}