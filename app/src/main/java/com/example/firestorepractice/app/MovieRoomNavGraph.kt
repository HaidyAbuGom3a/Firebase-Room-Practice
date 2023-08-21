package com.example.firestorepractice.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.firestorepractice.presentation.screens.home.homeRoute
import com.example.firestorepractice.presentation.screens.login.loginRoute
import com.example.firestorepractice.presentation.screens.profile.profileRoute
import com.example.firestorepractice.presentation.screens.room.join.joinRoomRoute
import com.example.firestorepractice.presentation.screens.room.roomRoute
import com.example.firestorepractice.presentation.screens.signup.signUpRoute

const val start = "signup"
@Composable
fun MovieRoomNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = start){
        signUpRoute(navController)
        loginRoute(navController)
        profileRoute(navController)
        homeRoute(navController)
        joinRoomRoute(navController)
        roomRoute()
    }
}