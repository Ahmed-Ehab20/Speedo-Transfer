package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.speedotransfer.R
import com.example.speedotransfer.screens.Stepper
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.*

@Composable
fun TransferSuccessPage(
    amount: String,
    currency: String,
    fromName: String,
    toName: String,
    fromAccount: String,
    toAccount: String,
    modifier: Modifier = Modifier
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(YellowGradientStart, PinkGradientEnd),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = 1,
                onItemSelected = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            // Title
            SpeedoTitleCard(title = "Transfer", navController = null)

            Spacer(modifier = Modifier.height(16.dp))

            // Stepper with 3 steps (Amount, Confirmation, Payment)
            Stepper(currentStep = 3)


            // Centered checkmark
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Success check mark image
                Image(
                    painter = painterResource(id = R.drawable.red_check),
                    contentDescription = "Success",
                    modifier = Modifier.size(80.dp)  // Adjust size of the check mark as needed
                )

                // Success message
                Text(
                    text = "Your Transfer has been successful",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 12.dp),
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // From & To card information
            TransactionCard(fromName, fromAccount, toName, toAccount)

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons in a column
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Buttons in a column
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // "Back to Home" Button using SpeedoButton
                    SpeedoButton(
                        label = "Back to Home",
                        onClick = { /* Navigate to Home Screen */ },
                        backgroundColor = Primary300,
                        textColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    )

                    // "Add to Favorite" Button using SpeedoButton with Transparent Background
                    SpeedoButton(
                        label = "Add to Favorite",
                        onClick = { /* Add to Favorite Logic */ },
                        backgroundColor = Color.Transparent,
                        textColor = Primary300,
                        borderColor = Primary300,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun TransactionCard(fromName: String, fromAccount: String, toName: String, toAccount: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // From Card
        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            colors = CardDefaults.cardColors(containerColor = Primary50)
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.bank), contentDescription = "Bank")
                Column(modifier = Modifier.padding(start = 32.dp)) {
                    Text(
                        text = "From",
                        color = Primary300,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        text = fromName,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = fromAccount,
                        fontSize = 16.sp,
                        color = Gray100,
                        fontWeight = FontWeight.W400
                    )
                }
            }
        }

        // Arrow Image (check mark centered)
        Image(
            painter = painterResource(id = R.drawable.check),
            contentDescription = "Check",
            modifier = Modifier
                .offset(0.dp, (-16.5).dp)
                .zIndex(1f)
        )

        // To Card
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .offset(0.dp, (-33).dp),
            colors = CardDefaults.cardColors(containerColor = Primary50)
        ) {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.bank), contentDescription = "Bank")
                Column(modifier = Modifier.padding(start = 32.dp)) {
                    Text(
                        text = "To",
                        color = Primary300,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    )
                    Text(
                        text = toName,
                        modifier = Modifier.padding(bottom = 8.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = toAccount,
                        fontSize = 16.sp,
                        color = Gray100,
                        fontWeight = FontWeight.W400
                    )
                }
            }
        }
    }
}

// Preview function
@Preview(showBackground = true)
@Composable
fun TransferSuccessPagePreview() {
    TransferSuccessPage(
        amount = "1000",
        currency = "EGP",
        fromName = "Asmaa Dosuky",
        toName = "Jonathon Smith",
        fromAccount = "Account xxxx7890",
        toAccount = "Account xxxx7890"
    )
}
