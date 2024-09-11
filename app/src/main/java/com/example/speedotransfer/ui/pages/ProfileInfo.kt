package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.speedotransfer.network.datamodel.ProfileInformationViewModel
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoTitleCard


@Composable
fun ProfileInformationScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(4) }  // Change to 4 for the "More" tab
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    val viewModel: ProfileInformationViewModel = viewModel()
    val profileInfo by viewModel.profileInfo.collectAsState()

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
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    SpeedoTitleCard(title = "Profile Information", navController = navController)

                    Spacer(modifier = Modifier.height(28.dp))

                    // Dynamically show the profile information
                    ProfileInfoItem(label = "Full Name", value = profileInfo.fullName)
                    ProfileInfoItem(label = "Email", value = profileInfo.email)
                    ProfileInfoItem(label = "Date Of Birth", value = profileInfo.dateOfBirth)
                    ProfileInfoItem(label = "Country", value = profileInfo.country)
                    ProfileInfoItem(label = "Bank Account", value = profileInfo.bankAccount)
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedItem = selectedItem,
                onItemSelected = { index -> selectedItem = index }
            )
        }
    )
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = Color.LightGray)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInformationScreenPreview() {
    // Provide a mock NavController for preview
    ProfileInformationScreen(navController = rememberNavController())
}
