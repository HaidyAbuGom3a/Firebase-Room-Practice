package com.example.firestorepractice.presentation.screens.profile

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "profile"

fun NavController.navigateToProfile(userId: String, email: String) {
    navigate("$ROUTE/$userId/$email") { popUpTo(ROUTE) { inclusive = true } }
}

fun NavGraphBuilder.profileRoute(navController: NavController) {
    composable(
        "$ROUTE/{${ProfileArgs.ID_ARG}}/{${ProfileArgs.EMAIL_ARG}}",
        arguments = listOf(
            navArgument(ProfileArgs.ID_ARG) { NavType.StringType },
            navArgument(ProfileArgs.EMAIL_ARG) { NavType.StringType })
    ) { ProfileScreen(navController = navController) }
}

class ProfileArgs(savedStateHandle: SavedStateHandle) {
    val id: String = requireNotNull(savedStateHandle[ID_ARG])

    companion object {
        const val ID_ARG = "id"
        const val EMAIL_ARG = "email"
    }
}