package com.example.speedotransfer.ui.pages

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.model.Transaction
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoTransaction
import com.example.speedotransfer.ui.elements.formatNumberWithCommas
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Gray200
import com.example.speedotransfer.ui.theme.Gray40
import com.example.speedotransfer.ui.theme.Gray900
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.YellowGradientStart
import java.util.Locale

@Composable
fun HomePage(
    navController: NavController,
    name: String,
    balance: String,
    currency: String,
    recentTransactions: List<Transaction> = emptyList(),
    modifier: Modifier = Modifier
) {
    var selectedItem = 0

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            0.0f to YellowGradientStart,
                            1.0f to PinkGradientEnd
                        )
                    )
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(0.9f)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = Gray40,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = extractInitials(name),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W600,
                                color = Gray100
                            )
                        }
                    }
                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        Text(
                            text = "Welcome back,",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = Primary300
                        )
                        Text(text = name, fontWeight = FontWeight.W500, color = Gray900)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.bell),
                        contentDescription = "Notification Bell",
                        Modifier.clickable { navController.navigate(Route.NOTIFICATIONS) }
                    )
                }
                Surface(
                    color = Primary300,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(horizontal = 13.dp, vertical = 24.dp)
                    ) {
                        Text(
                            text = "Current Balance",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.White
                        )
                        Text(
                            text = formatNumberWithCommas(balance) + currency,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.White,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth(0.9f)) {
                    Text(
                        text = "Recent transactions",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Gray900
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "View all",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Gray200,
                        modifier = Modifier.clickable { navController.navigate(Route.TRANSACTIONS) })
                }
                Surface(
                    color = Color.White, shape = RoundedCornerShape(8.dp), modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(vertical = 8.dp)
                ) {
                    LazyColumn {
                        items(recentTransactions) {
                            SpeedoTransaction(
                                name = it.name,
                                amount = it.amount,
                                date = it.date,
                                type = it.type,
                                cardDetails = it.cardDetails,
                                currency = it.currency,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable { navController.navigate(Route.VIEW_TRANSACTION) })
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController,
                selectedItem = selectedItem,
                onItemSelected = { index -> selectedItem = index })
        })


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun HomePagePreview() {

}

fun extractInitials(fullName: String): String {
    val names = fullName.split(" ")
    val firstNameInitial =
        names.firstOrNull()?.firstOrNull()?.toString()?.uppercase(Locale.ROOT) ?: ""
    val lastNameInitial =
        names.lastOrNull()?.firstOrNull()?.toString()?.uppercase(Locale.ROOT) ?: ""
    return firstNameInitial + lastNameInitial
}