package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Gray100
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.Gray900
import com.example.speedotransfer.ui.theme.Primary50

@Composable
fun SpeedoNotification(notificationTitle:String,notificationBody:String,notificationDate:String,modifier: Modifier = Modifier) {
    Card(colors=CardDefaults.cardColors(containerColor = Primary50), elevation = CardDefaults.cardElevation(defaultElevation = 16.dp), modifier = modifier, shape = RoundedCornerShape(8.dp)) {
        Row(modifier=Modifier.padding(vertical=16.dp,horizontal=8.dp)) {
            Card(colors=CardDefaults.cardColors(containerColor = Primary50), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), shape = RoundedCornerShape(8.dp)) {
                Image(painter = painterResource(id = R.drawable.notification_icon), contentDescription = "Notification",modifier=Modifier.padding(12.dp))
            }
            Column(modifier=Modifier.padding(start=16.dp)) {
                Text(text = notificationTitle, fontSize = 16.sp, fontWeight = FontWeight.W500,color= Gray900, modifier = Modifier.padding(bottom=4.dp))
                Text(text = notificationBody, fontSize = 14.sp, fontWeight = FontWeight.W400,color= Gray700, modifier = Modifier.padding(bottom=4.dp))
                Text(text = notificationDate, fontSize = 14.sp, fontWeight = FontWeight.W400,color= Gray100, modifier = Modifier.padding(bottom=4.dp))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SpeedoNotificationPreview() {
    Column(Modifier.background(Color.White).fillMaxSize().padding(vertical=16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        SpeedoNotification("Receive Transaction","You have received 1000 USD from Asmaa Dosuky 1234 xxx","12 Jul 2024 09:00 PM",modifier=Modifier.fillMaxWidth(0.9f))

    }
}