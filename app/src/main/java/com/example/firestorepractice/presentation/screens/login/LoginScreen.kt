package com.example.firestorepractice.presentation.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestorepractice.R
import com.example.firestorepractice.presentation.composable.VerticalSpacer24
import com.example.firestorepractice.presentation.composable.VerticalSpacer32
import com.example.firestorepractice.presentation.screens.home.navigateToHome
import com.google.firebase.auth.FirebaseUser

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    LoginContent(
        state = state,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onClickPasswordEyeIcon = viewModel::onClickPasswordEyeIcon,
        onClickLogin = viewModel::onClickLogin,
        navController
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    state: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onClickPasswordEyeIcon: () -> Unit,
    onClickLogin: (String, String, (FirebaseUser?) -> Unit) -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(stringResource(R.string.login), fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
        VerticalSpacer32()
        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChanged,
            label = { Text(stringResource(R.string.email_field)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        )
        VerticalSpacer24()
        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChanged,
            label = { Text(stringResource(R.string.password)) },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { onClickPasswordEyeIcon() },
                    painter = painterResource(id = R.drawable.show_password),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            visualTransformation = if (state.passwordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        VerticalSpacer24()
        val context = LocalContext.current
        Button(onClick = {
            onClickLogin(state.email, state.password) {
                if (it != null) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.login_message_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigateToHome(it.uid)
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
            Text(stringResource(R.string.login))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}