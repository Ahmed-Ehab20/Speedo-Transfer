package com.example.speedotransfer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.speedotransfer.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.InformationItem

@Composable
fun ProfileScreen() {

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEAEE)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBrush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Asmaa Dosuky",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                InformationItem(
                    icon = ImageVector.vectorResource(id = R.drawable.person),
                    title = "Personal information",
                    subtitle = "Your information"
                )

                Spacer(modifier = Modifier.height(8.dp))

                InformationItem(
                    icon = ImageVector.vectorResource(id = R.drawable.setting),
                    title = "Setting",
                    subtitle = "Change your settings"
                )

                Spacer(modifier = Modifier.height(8.dp))

                InformationItem(
                    icon = ImageVector.vectorResource(id = R.drawable.history1),
                    title = "Payment history",
                    subtitle = "View your transactions"
                )

                Spacer(modifier = Modifier.height(8.dp))

                InformationItem(
                    icon = ImageVector.vectorResource(id = R.drawable.favorite),
                    title = "My Favourite list",
                    subtitle = "View your favourites"
                )
            }

            var selectedIndex by remember { mutableIntStateOf(0) }

            BottomNavigationBar(
                selectedItem = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}