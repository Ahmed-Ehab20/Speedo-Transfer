package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.model.ProfileViewModel
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.InformationItem
import com.example.speedotransfer.ui.elements.SpeedoTitleCard

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    var selectedIndex by remember { mutableIntStateOf(4) }
    val username by viewModel.username.collectAsState()

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
                    SpeedoTitleCard(title = "Profile", navController = navController)

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        // Show username when available
                        if (username != null) {
                            UserProfileHeader(userName = username!!)
                        } else {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        // Personal Information - Navigates to profile_info
                        InformationItem(
                            icon = ImageVector.vectorResource(id = R.drawable.person),
                            title = "Personal information",
                            subtitle = "Your information",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Route.PROFILE_INFO)
                                }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Settings - Navigates to setting
                        InformationItem(
                            icon = ImageVector.vectorResource(id = R.drawable.setting),
                            title = "Setting",
                            subtitle = "Change your settings",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Route.SETTINGS)
                                }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Payment History - Could be navigated to in future
                        InformationItem(
                            icon = ImageVector.vectorResource(id = R.drawable.history),
                            title = "Payment history",
                            subtitle = "View your transactions",
                            modifier = Modifier.fillMaxWidth().clickable { navController.navigate(Route.TRANSACTIONS)  }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Favourite List - Could be navigated to in future
                        InformationItem(
                            icon = ImageVector.vectorResource(id = R.drawable.favorite),
                            title = "My Favourite list",
                            subtitle = "View your favourites",
                            modifier = Modifier.fillMaxWidth().clickable { navController.navigate( Route.FAVOURITES) }
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedItem = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    )
}


@Composable
fun UserProfileHeader(userName: String) {
    val initials = userName.split(" ").joinToString("") { it.first().toString() }.take(2)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = userName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}