package com.example.speedotransfer.ui.pages


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.network.datamodel.UserPreferences
import com.example.speedotransfer.network.retrofit.updatePassword
import com.example.speedotransfer.network.retrofit.updateUser
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard

@Composable
fun ChangePasswordScreen(navController: NavController) {
    var currentPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)
    val userId by userPreferences.userIdFlow.collectAsState(initial = null)

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Scaffold(
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientBrush)
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    SpeedoTitleCard(
                        title = "Change Password",
                        navController = navController
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = currentPassword,
                        onValueChange = { currentPassword = it },
                        label = { Text("Current Password") },
                        placeholder = { Text("Enter your current password") },
                        visualTransformation = if (currentPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = {
                                currentPasswordVisible = !currentPasswordVisible
                            }) {
                                Icon(
                                    imageVector = if (currentPasswordVisible) ImageVector.vectorResource(
                                        id = R.drawable.visibility_on
                                    ) else ImageVector.vectorResource(id = R.drawable.visibility_off),
                                    contentDescription = if (currentPasswordVisible) "Hide Password" else "Show Password"
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("New Password") },
                        placeholder = { Text("Enter your new password") },
                        visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                                Icon(
                                    imageVector = if (newPasswordVisible) ImageVector.vectorResource(
                                        id = R.drawable.visibility_on
                                    ) else ImageVector.vectorResource(id = R.drawable.visibility_off),
                                    contentDescription = if (newPasswordVisible) "Hide Password" else "Show Password"
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()

                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    SpeedoButton(
                        label = "Save",
                        onClick = {
                            userId?.let { id ->
                                updatePassword(id, currentPassword, newPassword) { message ->
                                    resultMessage = message
                                    Toast.makeText(context, resultMessage, Toast.LENGTH_LONG).show()
                                    if (message == "Update successful"){
                                        navController.navigate(Route.SIGN_IN)
                                    }

                                }
                            } ?: run {
                                Toast.makeText(context, "User ID not found", Toast.LENGTH_LONG).show()
                            }

                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(rememberNavController())
}
