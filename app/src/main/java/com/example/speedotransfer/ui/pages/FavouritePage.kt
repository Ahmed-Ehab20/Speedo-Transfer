package com.example.speedotransfer.ui.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.network.datamodel.Favourite
import com.example.speedotransfer.ui.elements.SpeedoFavourite
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.YellowGradientStart
import com.example.speedotransfer.R
import com.example.speedotransfer.model.BalanceViewModel
import com.example.speedotransfer.network.datamodel.FavouriteRequest
import com.example.speedotransfer.network.datamodel.FavouritesViewModel
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.Primary300
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritePage(navController: NavController, modifier: Modifier = Modifier,favouritesViewModel: FavouritesViewModel= viewModel(),balanceViewModel: BalanceViewModel= viewModel()) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var cardHolderName by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    val id by balanceViewModel.id.collectAsState()
    val favourites by favouritesViewModel.favourites.collectAsState()
    var selectedFavourite by remember{ mutableStateOf("") }
    LaunchedEffect(id) {
        if(id!=null)
            favouritesViewModel.fetchFavourites(id!!)
    }
    Scaffold(content={innerPadding-> Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd)
            )
            .padding(innerPadding)
    ) {
        SpeedoTitleCard(title = "Favourite",navController)
        Text(
            text = "Your favourite list",
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier
                .padding(top = 32.dp, bottom = 16.dp)
                .fillMaxWidth(0.9f),
            textAlign = TextAlign.Center
        )
        LazyColumn {
            items(favourites) {
                SpeedoFavourite(name = it.recipientName, account = it.recipientAccountNumber, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp), onUpdateClick = { selectedFavourite=it.id
                    showBottomSheet = true }, onDeleteClick = {favouritesViewModel.deleteFavourite(id!!,it.id)})
            }
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth(0.9f)
                    ) {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.edit),
                                contentDescription = "Edit",
                                tint = Primary300
                            )
                            Text(
                                text = "Edit",
                                fontSize = 20.sp,
                                color = Gray700,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        SpeedoTextField(
                            title = "Recipient Name",
                            label = "Enter Cardholder Name",
                            textValue = cardHolderName,
                            onTextChange = { cardHolderName = it },
                            modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
                        )
                        SpeedoTextField(
                            title = "Recipient Account",
                            label = "Enter Card Number",
                            textValue = cardNumber,
                            onTextChange = {   if (it.length <= 16) {
                                cardNumber = it.filter { char -> char.isDigit() }
                            } },
                            modifier = Modifier.padding(bottom = 32.dp),
                            type= KeyboardType.Decimal
                        )
                        SpeedoButton(
                            label = "Save",
                            modifier = Modifier.padding(bottom = 135.dp),
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                                favouritesViewModel.updateFavourite(id!!,selectedFavourite,
                                    FavouriteRequest(cardHolderName,cardNumber)
                                )
                            })
                        Spacer(modifier = Modifier
                            .height(135.dp)
                            .fillMaxWidth())

                    }
                }

            }
        }})

}
fun formatCardNumber(cardNumber: String): String {
    return cardNumber.chunked(4).joinToString(" ")
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun FavouritePagePreview() {
    FavouritePage(navController = rememberNavController())
}