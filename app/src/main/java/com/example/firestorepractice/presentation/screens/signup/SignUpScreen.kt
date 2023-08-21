package com.example.firestorepractice.presentation.screens.signup

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
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
import com.example.firestorepractice.presentation.composable.VerticalSpacer64
import com.example.firestorepractice.presentation.screens.login.navigateToLogin
import com.example.firestorepractice.presentation.screens.profile.navigateToProfile
import com.google.firebase.auth.FirebaseUser

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    SignUpContent(
        state = state,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onConfirmPasswordChanged = viewModel::onConfirmPasswordChanged,
        onClickPasswordEyeIcon = viewModel::onClickPasswordEyeIcon,
        onClickConfirmPasswordEyeIcon = viewModel::onClickConfirmPasswordEyeIcon,
        onClickSignUp = viewModel::onClickSignUp,
        onClickLogin = { navController.navigateToLogin()},
        navController
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(
    state: SignUpUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onConfirmPasswordChanged: (String) -> Unit,
    onClickPasswordEyeIcon: () -> Unit,
    onClickConfirmPasswordEyeIcon: () -> Unit,
    onClickSignUp: (String, String, String, (ResponseType, FirebaseUser?) -> Unit) -> Unit,
    onClickLogin: () -> Unit,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(stringResource(R.string.sign_up), fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
        VerticalSpacer32()
        OutlinedTextField(
            value = state.email,
            label = { Text(text = stringResource(id = R.string.email_field))},
            onValueChange = onEmailChanged,
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
        OutlinedTextField(
            value = state.confirmPassword,
            onValueChange = onConfirmPasswordChanged,
            label = { Text(stringResource(R.string.confirm_password)) },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { onClickConfirmPasswordEyeIcon() },
                    painter = painterResource(id = R.drawable.show_password),
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            visualTransformation = if (state.confirmPasswordVisibility) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Spacer(Modifier.weight(1f))
            Text("already have account ?")
            Spacer(Modifier.width(8.dp))
            Text(
                "login",
                color = Color.Blue,
                modifier = Modifier
                    .padding(end = 64.dp)
                    .clickable { onClickLogin() })
        }
        VerticalSpacer24()
        val context = LocalContext.current
        Button(onClick = {
            onClickSignUp(
                state.email,
                state.password,
                state.confirmPassword
            ) { response, user ->
                when (response) {
                    ResponseType.SUCCESS -> {
                        Toast.makeText(
                            context, context.getString(R.string.message), Toast.LENGTH_SHORT
                        ).show()
                        navController.navigateToProfile(email = state.email, userId = user!!.uid)
                    }

                    ResponseType.FAILED -> Toast.makeText(
                        context, context.getString(R.string.error_message), Toast.LENGTH_SHORT
                    ).show()

                    ResponseType.PASSWORD_NOT_MATCH -> Toast.makeText(
                        context,
                        context.getString(R.string.password_not_match),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }) {
            Text(stringResource(R.string.sign_up))
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}