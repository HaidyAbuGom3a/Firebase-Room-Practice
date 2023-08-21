package com.example.firestorepractice.presentation.screens.signup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "signUp"

fun NavGraphBuilder.signUpRoute(navController: NavController) {
    composable(ROUTE) { SignUpScreen(navController = navController) }
}
