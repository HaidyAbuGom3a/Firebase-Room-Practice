package com.example.firestorepractice.presentation.screens.room.join

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "join"

fun NavController.navigateToJoinRoom(userId: String) {
    navigate("$ROUTE/$userId") { popUpTo(ROUTE) { inclusive = true } }
}

fun NavGraphBuilder.joinRoomRoute(navController: NavController) {
    composable(
        "$ROUTE/{${JoinRoomArgs.ID_ARG}}",
        arguments = listOf(navArgument(JoinRoomArgs.ID_ARG) { NavType.StringType })
    ) { JoinRoomScreen(navController = navController) }
}

class JoinRoomArgs(savedStateHandle: SavedStateHandle) {
    val id: String = requireNotNull(savedStateHandle[ID_ARG])

    companion object {
        const val ID_ARG = "id"
    }
}