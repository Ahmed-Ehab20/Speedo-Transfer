package com.example.speedotransfer.ui.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.network.datamodel.FavouriteRequest
import com.example.speedotransfer.network.datamodel.FavouritesViewModel
import com.example.speedotransfer.network.datamodel.TransferViewModel
import com.example.speedotransfer.ui.pages.Stepper
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.*

@Composable
fun TransferSuccessPage(
    navController: NavController,
    amount: String,
    currency: String,
    fromName: String,
    toName: String,
    fromAccount: String,
    toAccount: String,
    id:String,
    modifier: Modifier = Modifier,
    favouritesViewModel: FavouritesViewModel= viewModel()
) {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(YellowGradientStart, PinkGradientEnd),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )
    val context= LocalContext.current

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController,
                selectedItem = 1,
                onItemSelected = {}
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.verticalScroll(rememberScrollState())){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBrush)
                    .padding(innerPadding)
                    .padding(12.dp)
            ) {
                // Title
                SpeedoTitleCard(title = "Transfer", navController = navController)

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


                // Transfer amount section (NEW SECTION)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Transfer amount",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                    Text(
                        text = "$amount $currency",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    thickness = 1.dp,
                    color = Color.Gray.copy(alpha = 0.5f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons in a column
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // "Back to Home" Button using SpeedoButton
                    SpeedoButton(
                        label = "Back to Home",
                        onClick = { navController.navigate(Route.HOME) },
                        backgroundColor = Primary300,
                        textColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                    )

                    // "Add to Favorite" Button using SpeedoButton with Transparent Background
                    SpeedoButton(
                        label = "Add to Favorite",
                        onClick = { favouritesViewModel.addFavourite(userId =id, FavouriteRequest(recipientName = toName, recipientAccountNumber = toAccount)
                        )
                                  Toast.makeText(context,"favourite added",Toast.LENGTH_SHORT).show()
                                  navController.navigate(Route.TRANSFER_SCREEN)},
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
//
}
