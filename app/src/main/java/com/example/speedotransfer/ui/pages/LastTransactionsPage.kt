package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.model.BalanceViewModel
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.network.api.TransactionsAPIService
import com.example.speedotransfer.network.datamodel.TransactionsViewModel
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoLastTransaction
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.elements.SpeedoTransaction
import com.example.speedotransfer.ui.theme.Gray900
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun LastTransactionsPage(navController: NavController, modifier: Modifier = Modifier,transactionsViewModel: TransactionsViewModel=viewModel(),balanceViewModel: BalanceViewModel=viewModel()) {
    var selectedItem by remember { mutableStateOf(4) }
    val transactions by transactionsViewModel.transactions.collectAsState()
    val name by balanceViewModel.name.collectAsState()
    val currency by balanceViewModel.currency.collectAsState()
    Scaffold(content = {paddingValues -> Column(modifier = Modifier
        .fillMaxSize()
        .background(Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd))
        .padding(paddingValues), horizontalAlignment = Alignment.CenterHorizontally) {
        SpeedoTitleCard(title = "Transactions",navController,modifier=Modifier.fillMaxWidth(0.9f))
        Text(text = "Your Last Transactions", fontSize = 20.sp, fontWeight = FontWeight.W600,modifier=Modifier.padding(top=32.25.dp))
        if(transactions!=null&& transactions.isNotEmpty()){
        LazyColumn (modifier= Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 16.dp)){

                items(transactions) {
                    SpeedoLastTransaction(
                        name = it.recipientName!!,
                        amount = it.amount.toString(),
                        date = formatDateTime(it.transactionDate),
                        type = "Recieved",
                        cardDetails = "Visa . Mater Card . " + it.accountNumber.takeLast(
                            4
                        ),
                        isSuccessful = true,
                        isCard = true,
                        currency = currency!!,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { navController.navigate("${Route.VIEW_TRANSACTION}/${it.transactionId}") })
                }
            }

        }
        else {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(vertical = 8.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No transactions",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray900
                    )
                }
            }
        }
    } }, bottomBar = { BottomNavigationBar(navController,selectedItem = selectedItem, onItemSelected = { index -> selectedItem = index }) })

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LastTransactionsPagePreview() {
    LastTransactionsPage(navController = rememberNavController())
}