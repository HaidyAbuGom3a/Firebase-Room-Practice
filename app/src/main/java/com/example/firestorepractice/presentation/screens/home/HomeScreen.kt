package com.example.firestorepractice.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestorepractice.R
import com.example.firestorepractice.presentation.composable.VerticalSpacer24
import com.example.firestorepractice.presentation.composable.VerticalSpacer64
import com.example.firestorepractice.presentation.screens.profile.UserUiState
import com.example.firestorepractice.presentation.screens.room.join.navigateToJoinRoom
import com.example.firestorepractice.presentation.screens.room.navigateToRoom

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    HomeContent(
        state = state,
        onClickCreateRoom = viewModel::onClickCreateRoom,
        onClickJoinRoom = {navController.navigateToJoinRoom(state.id)},
        navController
    )
}

@Composable
fun HomeContent(
    state: UserUiState,
    onClickCreateRoom: ((Boolean, String?) -> Unit) -> Unit,
    onClickJoinRoom: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.welcome) + state.name)
        VerticalSpacer24()
        Text(stringResource(R.string.email) + state.email)
        VerticalSpacer64()
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            val context = LocalContext.current
            Button(onClick = {
                onClickCreateRoom { response, roomId ->
                    if (response) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.room_success_msg),
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigateToRoom(roomId!!,state.id)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.error_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            ) {
                Text(stringResource(R.string.create_room))
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(onClick = onClickJoinRoom) {
                Text(text = stringResource(R.string.join_room))
            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}