package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.model.Notification
import com.example.speedotransfer.ui.elements.BottomNavigationBar
import com.example.speedotransfer.ui.elements.SpeedoNotification
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun NotificationPage(navController: NavController,notifications:List<Notification> = emptyList(), modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }
    Scaffold(content={innerpadding-> Column(modifier= modifier
        .background(Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd))
        .padding(innerpadding)
        .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        SpeedoTitleCard(title = "Notifications",navController)
        LazyColumn(modifier= Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 24.dp)) {
            items(notifications){
                SpeedoNotification(notificationTitle = it.notificationTitle, notificationBody = it.notificationBody, notificationDate = it.notificationDate, modifier=Modifier.padding(bottom=16.dp))
            }
        }
    }}, bottomBar = { BottomNavigationBar(navController,selectedItem = selectedItem, onItemSelected = { index -> selectedItem = index }) })


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NotificationPagePreview() {

}