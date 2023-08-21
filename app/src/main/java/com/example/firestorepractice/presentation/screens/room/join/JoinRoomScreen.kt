package com.example.firestorepractice.presentation.screens.room.join

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestorepractice.R
import com.example.firestorepractice.presentation.composable.VerticalSpacer24
import com.example.firestorepractice.presentation.composable.VerticalSpacer32
import com.example.firestorepractice.presentation.screens.room.navigateToRoom

@Composable
fun JoinRoomScreen(
    navController: NavController,
    viewModel: JoinRoomViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    JoinRoomContent(
        state = state,
        onIdChanged = viewModel::onIdChanged,
        onClickJoin = viewModel::onClickJoin,
        navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinRoomContent(
    state: String,
    onIdChanged: (String) -> Unit,
    onClickJoin: ((Boolean, String) -> Unit) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            stringResource(R.string.join_room_title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )
        VerticalSpacer32()
        OutlinedTextField(
            value = state,
            onValueChange = onIdChanged,
            label = { Text(stringResource(R.string.room_id)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        )
        VerticalSpacer24()
        Button(onClick = {
            onClickJoin { success, userId ->
                if (success) {
                    navController.navigateToRoom(state, userId)
                } else {
                    Log.e("haidy", "something went wrong")
                }
            }
        }
        ) {
            Text(stringResource(R.string.join))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}