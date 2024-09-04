package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Gray40
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.Gray900
import com.example.speedotransfer.ui.theme.Primary300
import java.text.DecimalFormat

@Composable
fun SpeedoTransaction(
    name: String,
    amount: String,
    date: String,
    type: String,
    cardDetails: String,
    currency: String = "EGP",
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.mastercard),
                contentDescription = "Mastercard logo",
                modifier = Modifier
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .height(61.dp)
                    .fillMaxWidth(0.6f)
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
                )
                Text(
                    text = "$date - $type",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = Gray100,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = formatNumberWithCommas(amount) + currency,
                color = Primary300,
                modifier = Modifier
                    .padding(start = 8.dp),
                fontWeight = FontWeight.W500,
                maxLines = 1,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,

            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 8.dp),
            color = Gray40
        )
    }
}




@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SpeedoTransactionPreview() {
SpeedoTransaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234",modifier=Modifier.fillMaxWidth(0.8f))
}

fun formatNumberWithCommas(numberString: String): String {
    val number = numberString.toDoubleOrNull() ?: return numberString
    val formatter = DecimalFormat("#,###")
    return formatter.format(number)
}