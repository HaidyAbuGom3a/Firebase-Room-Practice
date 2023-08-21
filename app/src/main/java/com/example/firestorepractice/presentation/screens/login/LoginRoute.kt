package com.example.firestorepractice.presentation.screens.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "login"

fun NavController.navigateToLogin() {
    navigate(ROUTE) { popUpTo(ROUTE) { inclusive = true } }
}

fun NavGraphBuilder.loginRoute(navController: NavController) {
    composable(ROUTE) { LoginScreen(navController = navController) }
}
