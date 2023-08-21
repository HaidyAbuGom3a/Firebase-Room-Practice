package com.example.firestorepractice.data.repository

import com.example.firestorepractice.domain.repository.AuthRepository
import com.example.firestorepractice.domain.source.FirebaseDataSource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
): AuthRepository {
    override suspend fun signUp(email: String, password: String): FirebaseUser? =
        firebaseDataSource.signUp(email, password)

    override suspend fun login(email: String, password: String): FirebaseUser? =
        firebaseDataSource.login(email, password)
}