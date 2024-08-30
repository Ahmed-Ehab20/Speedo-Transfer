package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Danger300
import com.example.speedotransfer.ui.theme.FailBackground
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Gray40
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.Gray900
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.Primary50
import com.example.speedotransfer.ui.theme.SuccessBackground
import com.example.speedotransfer.ui.theme.SuccessText
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun SpeedoLastTransaction(
    name: String,
    amount: String,
    date: String,
    type: String,
    cardDetails: String,
    currency: String = "EGP",
    isSuccessful:Boolean,
    isCard:Boolean,
    modifier: Modifier = Modifier,

) {
    Card(modifier=modifier, colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(8.dp), elevation = CardDefaults.cardElevation(4.dp) ) {
        //TODO Need to implement the clickable card, need to ask mentor if the arrow button should be clickable or the whole card
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Image(
                    painter = if (isCard) painterResource(id = R.drawable.card_transfer) else painterResource(id = R.drawable.bank_transfer),
                    contentDescription = "Mastercard logo",
                    modifier = Modifier
                        .padding(end = 8.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = Gray900,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = cardDetails,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray700,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        , modifier = Modifier.padding(top=4.dp)
                    )
                    Text(
                        text = "$date - $type",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray100,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                        , modifier = Modifier.padding(top=4.dp)
                    )
                    Text(
                        text = formatNumberWithCommas(amount) + currency,
                        color = Primary300,
                        modifier = Modifier
                            .padding(top=8.dp),
                        fontWeight = FontWeight.W500,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,

                        )
                }
                Column {
                    Image(painter = painterResource(id = R.drawable.next_arrow), contentDescription = "next",modifier=Modifier.align(Alignment.End))
                    if (isSuccessful)
                    Card (colors = CardDefaults.cardColors(contentColor = SuccessText, containerColor = SuccessBackground), shape = RoundedCornerShape(5.35.dp), modifier = Modifier.padding(top=11.dp)){

                        Text(text = "Successful",modifier=Modifier.padding(vertical = 2.67.dp,horizontal=10.7.dp), fontSize = 9.36.sp)
                    }
                    else
                        Card (colors = CardDefaults.cardColors(contentColor = Danger300, containerColor = FailBackground), shape = RoundedCornerShape(5.35.dp), modifier = Modifier.padding(top=11.dp)){

                            Text(text = "Failed",modifier=Modifier.padding(vertical = 2.67.dp,horizontal=10.7.dp), fontSize = 9.36.sp)
                        }
                }
            }

        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SpeedoLastTransactionPreview() {
    Column(
        Modifier
            .background(YellowGradientStart)
            .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        SpeedoLastTransaction(name = "Ahmed Mohamed Mohammed Mohammed", isCard = true, amount = "100000", date = "Today 11:00", type = "Received" , cardDetails ="Visa . Mater Card . 1234" , isSuccessful = false ,modifier=Modifier.fillMaxWidth(0.9f).padding(top=30.dp))

    }
}