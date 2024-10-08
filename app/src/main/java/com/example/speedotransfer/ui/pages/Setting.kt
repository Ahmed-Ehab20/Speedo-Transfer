package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.ui.elements.InformationItem
import com.example.speedotransfer.ui.elements.SpeedoTitleCard

@Composable
fun SettingsScreen(navController: NavController) {
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
                    .background(brush = gradientBrush).padding(paddingValues)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    SpeedoTitleCard(
                        title = "Settings",
                        navController = navController
                    )
                    Spacer(modifier = Modifier.height(28.dp))

                    InformationItem(
                        icon = ImageVector.vectorResource(id = R.drawable.lock),
                        title = "Change password",
                        subtitle = "Change your password",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Route.CHANGE_PASSWORD) }
                    )

                    // Navigate to Edit Profile screen
                    InformationItem(
                        icon = ImageVector.vectorResource(id = R.drawable.edit_profile),
                        title = "Edit Profile",
                        subtitle = "Change your information",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Route.EDIT_PROFILE) }
                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}
