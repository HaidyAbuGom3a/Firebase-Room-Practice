package com.example.firestorepractice.presentation.screens.room

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestorepractice.R
import com.example.firestorepractice.presentation.composable.VerticalSpacer24
import com.example.firestorepractice.presentation.composable.VerticalSpacer32

@Composable
fun RoomScreen(
    viewModel: RoomViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    RoomContent(state = state)
}

@Composable
fun RoomContent(
    state: RoomUiState
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.room_id_title) + state.roomId,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            stringResource(R.string.users_title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )
        VerticalSpacer24()
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.users) {
                ItemUser(name = it.name)
            }
        }
        VerticalSpacer32()
        Button(onClick = {}, enabled = state.visible) {
            Text(stringResource(R.string.play_video))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ItemUser(name: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
    ) {
        Text(
            name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}