package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.Primary50
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransferScreen(navController: NavController) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    // State management for the bottom sheet
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    // State for recipient name and account
    var recipientName by remember { mutableStateOf("") }
    var recipientAccount by remember { mutableStateOf("") }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()), // Make the sheet scrollable
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Row containing the star drawable and "Select a Favorite" text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star), // Replace with your star drawable resource ID
                        contentDescription = "Star",
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "Favorites List",
                        fontSize = 27.sp,
                        color = Primary300,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Center the cards and add padding
                FavoriteCard(
                    fromName = "John Doe",
                    fromAccount = "123456789",
                    onCardClick = { name, account ->
                        recipientName = name
                        recipientAccount = account
                        coroutineScope.launch {
                            bottomSheetState.hide() // Hide the bottom sheet when a card is selected
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                FavoriteCard(
                    fromName = "Alice Johnson",
                    fromAccount = "123123123",
                    onCardClick = { name, account ->
                        recipientName = name
                        recipientAccount = account
                        coroutineScope.launch {
                            bottomSheetState.hide() // Hide the bottom sheet when a card is selected
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    navController,
                    selectedItem = 1,
                    onItemSelected = { /* Handle item selection */ }
                )
            },
            content = { innerPadding ->
                Column(modifier = Modifier.fillMaxSize().background(gradientBrush), horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.93f)
                            .fillMaxHeight()
                            .padding(innerPadding)
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

                        // Row for Recipient Information and Favorites button
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Recipient Information",
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                                    .padding(bottom = 8.dp)
                            )

                            // Star drawable and Favorites text
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.star), // Replace with your star drawable resource ID
                                    contentDescription = "Star",
                                    modifier = Modifier
                                        .size(18.dp)
                                        .padding(end = 4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    color = Primary300,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .clickable {
                                            coroutineScope.launch {
                                                bottomSheetState.show() // Show the bottom sheet when text is clicked
                                            }
                                        }
                                        .padding(4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

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
                            onClick = { navController.navigate(Route.TRANSFER_CONFIRMATION) }
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun FavoriteCard(
    fromName: String,
    fromAccount: String,
    onCardClick: (String, String) -> Unit // Add a click handler
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable { onCardClick(fromName, fromAccount) }, // Handle click
        colors = CardDefaults.cardColors(containerColor = Primary50)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.bank), contentDescription = "Bank")
            Column(modifier = Modifier.padding(start = 32.dp)) {
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
}

@Preview(showBackground = true)
@Composable
fun TransferScreenPreview() {
    val navController = rememberNavController() // Mock navigation controller for preview
    TransferScreen(navController = navController)
}
