package com.example.speedotransfer.ui.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.network.datamodel.UpdateUserRequest
import com.example.speedotransfer.network.datamodel.UserPreferences
import com.example.speedotransfer.network.retrofit.updateUser
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard

@Composable
fun EditProfileScreen(navController: NavController) {

    val context = LocalContext.current
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var country by rememberSaveable { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }
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
                        title = "Edit Profile",
                        navController = navController
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it},
                        label = { Text("Full Name") },
                        placeholder = { Text("Enter Cardholder Name") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = {email = it},
                        label = { Text("Email") },
                        placeholder = { Text("Enter Email") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = country,
                        onValueChange = {country = it},
                        label = { Text("Country") },
                        readOnly = false,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = "12/02/2000",
                        onValueChange = {},
                        label = { Text("Date Of Birth") },
                        trailingIcon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.calendar),
                                contentDescription = "Calendar"
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    SpeedoButton(
                        label = "Save",
                        onClick = {
                            userId?.let { id ->
                                updateUser(id, fullName, email, country) { message ->
                                    resultMessage = message
                                    Toast.makeText(context, resultMessage, Toast.LENGTH_LONG).show()
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
fun EditProfileScreenPreview() {
//    EditProfileScreen(rememberNavController())
}
