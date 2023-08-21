package com.example.firestorepractice.presentation.screens.profile

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestorepractice.R
import com.example.firestorepractice.presentation.composable.VerticalSpacer24
import com.example.firestorepractice.presentation.composable.VerticalSpacer32
import com.example.firestorepractice.presentation.screens.home.navigateToHome

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ProfileContent(
        state = state,
        onNameChanged = viewModel::onNameChanged,
        onClickCreateAccount = viewModel::onClickCreateAccount,
        navController = navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    state: UserUiState,
    onNameChanged: (String) -> Unit,
    onClickCreateAccount: ((Boolean, String) -> Unit) -> Unit,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            stringResource(R.string.create_account),
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )
        VerticalSpacer32()
        OutlinedTextField(
            value = state.name,
            label = { Text(text = stringResource(R.string.name))},
            onValueChange = onNameChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        )
        VerticalSpacer24()
        val context = LocalContext.current
        Button(
            onClick = {
                onClickCreateAccount { response, id ->
                    if (response) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.create_account_success),
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigateToHome(id)
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
            Text(stringResource(R.string.create_account_btn))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}