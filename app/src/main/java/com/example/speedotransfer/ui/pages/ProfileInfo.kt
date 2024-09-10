// ProfileInformationScreen.kt
package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.model.ProfileInformationViewModel
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ProfileInformationScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(3) }
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    // Collect profile information from the ViewModel
    val profileInfo by viewModel.profileInfo.collectAsState()

    Scaffold(
        topBar = {
            SpeedoTitleCard(title = "Profile Information", navController = navController)
        },
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
        }, bottomBar = { BottomNavigationBar(navController,selectedItem = selectedItem, onItemSelected = { index -> selectedItem = index }) }
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
