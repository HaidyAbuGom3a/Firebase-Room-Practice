package com.example.firestorepractice.presentation.screens.room

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "room"

fun NavController.navigateToRoom(roomId: String, userId: String) {
    navigate("$ROUTE/$roomId/$userId") { popUpTo("home") { inclusive = false } }
}

fun NavGraphBuilder.roomRoute() {
    composable(
        "$ROUTE/{${RoomArgs.ROOM_ID_ARG}}/{${RoomArgs.USER_ID_ARD}}",
        arguments = listOf(
            navArgument(RoomArgs.ROOM_ID_ARG) { NavType.StringType },
            navArgument(RoomArgs.USER_ID_ARD) { NavType.StringType }
        )
    ) { RoomScreen() }
}

class RoomArgs(savedStateHandle: SavedStateHandle) {
    val roomId: String = requireNotNull(savedStateHandle[ROOM_ID_ARG])
    val userId: String = requireNotNull(savedStateHandle[USER_ID_ARD])

    companion object {
        const val ROOM_ID_ARG = "roomId"
        const val USER_ID_ARD = "userId"
    }
}