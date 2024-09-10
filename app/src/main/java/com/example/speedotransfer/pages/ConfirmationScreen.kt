package com.example.speedotransfer.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.Primary50

@Composable
fun ConfirmationScreen(
    navController: NavController? = null,
    amount: String,
    fromName: String = "Asmaa Desouky",
    fromAccount: String = "xxxx1234",
    recipientName: String,
    recipientAccount: String
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(gradientBrush)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp)
                    .zIndex(1f)
            ) {
                SpeedoTitleCard(title = "Transfer", navController = navController)

                Spacer(modifier = Modifier.height(16.dp))

                Stepper(currentStep = 2)

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "$amount EGP",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Transfer amount",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total Amount",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$amount EGP",
                        fontSize = 16.sp,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.96f)
                        .align(Alignment.CenterHorizontally),
                    colors = CardDefaults.cardColors(containerColor = Primary50)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bank),
                            contentDescription = "Bank"
                        )
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 8.dp)
                        .zIndex(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellipse_2),
                        contentDescription = "Ellipse Background",
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                            .offset(y = (-24.dp))
                    )
                    
                    Image(
                        painter = painterResource(id = R.drawable.transfer_money),
                        contentDescription = "Complete",
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center)
                            .offset(y = (-24.dp))
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.96f)
                        .align(Alignment.CenterHorizontally)
                        .offset(y = (-48.dp)),
                    colors = CardDefaults.cardColors(containerColor = Primary50)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bank),
                            contentDescription = "Bank"
                        )
                        Column(modifier = Modifier.padding(start = 32.dp)) {
                            Text(
                                text = "To",
                                color = Primary300,
                                modifier = Modifier.padding(bottom = 8.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500
                            )
                            Text(
                                text = recipientName,
                                modifier = Modifier.padding(bottom = 8.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W600
                            )
                            Text(
                                text = recipientAccount,
                                fontSize = 16.sp,
                                color = Gray100,
                                fontWeight = FontWeight.W400
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    SpeedoButton(
                        label = "Confirm",
                        onClick = { },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )

                    SpeedoButton(
                        label = "Previous",
                        onClick = {
                            navController?.popBackStack()
                        },
                        backgroundColor = Color.Transparent,
                        textColor = Primary300,
                        borderColor = Primary300,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .offset(y = 60.dp) 
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationScreenPreview() {
    ConfirmationScreen(
        amount = "1000",
        recipientName = "Jonathon Smith",
        recipientAccount = "xxxx7890"
    )
}
