package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.model.Transaction
import com.example.speedotransfer.ui.elements.SpeedoLastTransaction
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun LastTransactionsPage(transactions:List<Transaction> = emptyList(),modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd)), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top=78.dp),
            verticalAlignment = Alignment.CenterVertically // Align items vertically center
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Back"
            )
            Spacer(modifier = Modifier.weight(1f)) // Pushes the text to the center
            Text(
                text = "Transactions",
                fontSize = 20.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.align(Alignment.CenterVertically) // Align text vertically center
            )
            Spacer(modifier = Modifier.weight(1f)) // Ensures the text remains centered
        }
        Text(text = "Your Last Transactions", fontSize = 20.sp, fontWeight = FontWeight.W600,modifier=Modifier.padding(top=32.25.dp))
        LazyColumn (modifier=Modifier.fillMaxWidth(0.9f).padding(top=16.dp)){
            items(transactions){
                SpeedoLastTransaction(
                    name = it.name,
                    amount = it.amount,
                    date = it.date,
                    type = it.type,
                    cardDetails = it.cardDetails,
                    isSuccessful = it.isSuccessful,
                    isCard = it.isCard,
                    modifier=Modifier.padding(bottom=16.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LastTransactionsPagePreview() {
    val t1 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
    val t2 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = false, isSuccessful = true)
    val t3 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = false, isSuccessful = false)
    val t4 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
    val t5 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = false, isSuccessful = false)
    val t6 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = false, isSuccessful = false)
    val t7 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
    val t8 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = false, isSuccessful = true)
    val transactions= listOf(t1,t2,t3,t4,t5,t6,t7,t8)
    LastTransactionsPage(transactions)
}