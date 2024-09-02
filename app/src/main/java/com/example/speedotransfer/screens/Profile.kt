package com.example.speedotransfer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.InformationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEAEE)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBrush)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    UserProfileHeader(userName = "Ahmed Ehab")

                    Spacer(modifier = Modifier.height(16.dp))

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
                        icon = ImageVector.vectorResource(id = R.drawable.history),
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
    ProfileScreen()
}