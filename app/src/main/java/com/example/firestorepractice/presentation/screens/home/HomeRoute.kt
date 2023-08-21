package com.example.firestorepractice.presentation.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE = "home"

fun NavController.navigateToHome(userId: String) {
    navigate("$ROUTE/$userId") { popUpTo("signUp") { inclusive = false } }
}

fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(
        "$ROUTE/{${HomeArgs.ID_ARG}}",
        arguments = listOf(navArgument(HomeArgs.ID_ARG) { NavType.StringType })
    ) { HomeScreen(navController = navController) }
}

class HomeArgs(savedStateHandle: SavedStateHandle) {
    val id: String = requireNotNull(savedStateHandle[ID_ARG])

    companion object {
        const val ID_ARG = "id"
    }
}