package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.Primary300

@Composable
fun MyCardsScreen(navController: NavController) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedItem = 3,
                onItemSelected = { /* Handle item selection */ }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBrush)
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SpeedoTitleCard(title = "My Cards", navController = navController)

                    Spacer(modifier = Modifier.height(24.dp))

                    FavoriteCard(
                        fromName = "Asmaa Dosuky",
                        fromAccount = "Account xxxx7890",
                        onCardClick = { fromName, fromAccount ->
                            // Handle card click (e.g., show details or navigate)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Add any additional cards as needed
                }

                SpeedoButton(
                    label = "Add New Account",
                    onClick = { /* Handle button click */ },
                    backgroundColor = Primary300,
                    textColor = Color.White,
                    borderColor = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth(0.93f)
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyCardsScreenPreview() {
    val navController = rememberNavController()
    MyCardsScreen(navController = navController)
}
