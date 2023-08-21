package com.example.firestorepractice.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signUp(email: String, password: String): FirebaseUser?
    suspend fun login(email: String, password: String): FirebaseUser?
}