package com.example.firestorepractice.domain.usecase

import com.example.firestorepractice.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend operator fun invoke(email: String, password: String) =
        authRepository.login(email, password)
}