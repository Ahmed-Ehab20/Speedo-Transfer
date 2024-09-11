package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.model.BalanceViewModel
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.network.datamodel.FavouriteRequest
import com.example.speedotransfer.network.datamodel.FavouritesViewModel
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoFavourite
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Gray700
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(
    navController: NavController,
    balanceViewModel: BalanceViewModel = viewModel(),
    favouritesViewModel: FavouritesViewModel = viewModel()
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF7E7), Color(0xFFFAE7E8)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )
    val id by balanceViewModel.id.collectAsState()
    val favourites by favouritesViewModel.favourites.collectAsState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var recipientName by remember { mutableStateOf("") }
    var recipientAccount by remember { mutableStateOf("") }
    LaunchedEffect(id) {
        if (id != null) {
            favouritesViewModel.fetchFavourites(id.toString())
        }
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController,
                selectedItem = 1,
                onItemSelected = { /* Handle item selection */ }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBrush), horizontalAlignment = Alignment.CenterHorizontally
            ) {
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

                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = "Recipient Information",
                            fontSize = 16.sp,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(onClick = { showBottomSheet = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                contentDescription = "favourite",
                                modifier = Modifier.size(20.dp),
                                tint = Primary300
                            )
                            Text(text = "Favourite", color = Primary300)
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_right),
                                contentDescription = "Next",
                                modifier = Modifier.size(16.dp),
                                tint = Primary300
                            )
                        }
                    }



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

                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    val isButtonEnabled =
                        amount.isNotBlank() && recipientName.isNotBlank() && recipientAccount.isNotBlank()
                    SpeedoButton(
                        label = "Continue",
                        enabled = isButtonEnabled,
                        onClick = { navController.navigate("${Route.TRANSFER_CONFIRMATION}/$amount/$recipientName/$recipientAccount") }
                    )
                }

            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    shape = RoundedCornerShape(
                        topStart = 50.dp,
                        topEnd = 50.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ),
                    windowInsets = WindowInsets.waterfall,
                    containerColor = Color.White

                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                contentDescription = "Favourite",
                                tint = Primary300
                            )
                            Text(text = "Favourite List", color = Primary300)
                        }
                        LazyColumn(modifier = Modifier.padding(top = 16.dp, bottom = 40.dp)) {
                            items(favourites) {
                                SpeedoFavourite(
                                    name = it.recipientName,
                                    account = it.recipientAccountNumber,
                                    iconsShown = false,
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .clickable {
                                            recipientName = it.recipientName
                                            recipientAccount = it.recipientAccountNumber
                                            showBottomSheet = false
                                        }
                                        .padding(bottom = 16.dp)
                                )
                            }
                        }
                    }

                }

            }
        }
    )
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
