package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.Primary300

@Composable
fun Stepper(currentStep: Int) {
    val steps = listOf("Amount", "Confirmation", "Payment")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        steps.forEachIndexed { index, step ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .border(
                            width = 2.dp,
                            color = if (index + 1 <= currentStep) Primary300 else Color.Gray,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        color = if (index + 1 <= currentStep) Primary300 else Color.Gray,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = step,
                    fontSize = 14.sp,
                    color = if (index + 1 <= currentStep) Color.Black else Color.Gray
                )
            }
            if (index < steps.size - 1) {
                Spacer(modifier = Modifier.width(24.dp))
                Divider(
                    color = if (index + 1 < currentStep) Primary300 else Color.Gray,
                    modifier = Modifier
                        .width(40.dp)
                        .height(2.dp)
                )
                Spacer(modifier = Modifier.width(24.dp))
            }
        }
    }
}

@Composable
fun TransferScreen(navController: NavController? = null) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = 1,
                onItemSelected = { /* Handle item selection */ }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(gradientBrush)
                .padding(16.dp)
        ) {
            SpeedoTitleCard(title = "Transfer", navController = navController)

            Spacer(modifier = Modifier.height(16.dp))

            Stepper(currentStep = 1)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "How much are you sending?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Amount",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            var amount by remember { mutableStateOf("") }
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                placeholder = { Text("Enter amount") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Recipient Information",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            var recipientName by remember { mutableStateOf("") }
            OutlinedTextField(
                value = recipientName,
                onValueChange = { recipientName = it },
                label = { Text("Recipient Name") },
                placeholder = { Text("Enter Recipient Name") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            var recipientAccount by remember { mutableStateOf("") }
            OutlinedTextField(
                value = recipientAccount,
                onValueChange = { recipientAccount = it },
                label = { Text("Recipient Account") },
                placeholder = { Text("Enter Recipient Account Number") },
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
                label = "Continue",
                onClick = { /* Handle continue action */ }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TransferScreenPreview() {
    TransferScreen()
}