package com.example.speedotransfer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.InformationItem
import com.example.speedotransfer.ui.elements.SpeedoTitleCard

@Composable
fun SettingsScreen() {
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
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    // Use SpeedoTitleCard for the title
                    SpeedoTitleCard(title = "Setting")

                    Spacer(modifier = Modifier.height(28.dp))

                    // Information items
                    InformationItem(
                        icon = ImageVector.vectorResource(id = R.drawable.lock),
                        title = "Change password",
                        subtitle = "Change password",
                        modifier = Modifier.fillMaxWidth()
                    )

                    InformationItem(
                        icon = ImageVector.vectorResource(id = R.drawable.edit_profile),
                        title = "Edit Profile",
                        subtitle = "Change your information",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}
