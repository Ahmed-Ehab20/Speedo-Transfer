package com.example.speedotransfer.ui.pages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.Primary50
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun ViewTransactionPage(
    navController: NavController,
    amount: String,
    currency: String,
    isRecieved: Boolean,
    fromName: String,
    toName: String,
    fromAccount: String,
    toAccount: String,
    referenceNumber: String,
    date: String,
    modifier: Modifier = Modifier
) {
    var selectedItem by remember { mutableStateOf(2) }
    Scaffold(content={innerPadding->Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd)
            ).padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.d("trace2", innerPadding.toString())
        SpeedoTitleCard(title = "Successful Transactions",navController)
        Image(
            painter = painterResource(id = R.drawable.success),
            contentDescription = "Sucessful Transaction",
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )
        Text(
            text = AnnotatedString.Builder().apply {
                append(amount)
                withStyle(style = SpanStyle(color = Primary300)) {
                    append(" $currency")
                }
            }.toAnnotatedString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Transfer amount",
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = if (isRecieved) "Recieved money" else "Sent money",
            color = Primary300,
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
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
            Image(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "Complete",
                modifier = Modifier
                    .offset(0.dp, (-16.5).dp)
                    .zIndex(1f)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
                    .offset(0.dp, (-33).dp),
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
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .offset(0.dp, (-33).dp),
            colors = CardDefaults.cardColors(containerColor = Primary50)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = "Reference",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray700
                    )
                    Text(
                        text = referenceNumber,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray100
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Date",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray700
                    )
                    Text(
                        text = date,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray100
                    )
                }
            }
        }
    }}, bottomBar = { BottomNavigationBar(navController,selectedItem = selectedItem, onItemSelected = { index -> selectedItem = index }) })



}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ViewTransactionPagePreview() {

}