package com.example.firestorepractice.presentation.screens.home

import com.example.firestorepractice.domain.entity.UserEntity
import com.example.firestorepractice.presentation.screens.profile.UserUiState

fun UserEntity.toUserUiState() = UserUiState(
    id = id ?: "",
    name = name ?: "",
    email = email ?: ""
)