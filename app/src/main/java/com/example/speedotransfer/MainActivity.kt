package com.example.speedotransfer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.speedotransfer.model.Notification
import com.example.speedotransfer.model.Transaction
import com.example.speedotransfer.ui.pages.HomePage
import com.example.speedotransfer.ui.pages.NotificationPage
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedoTransferTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val n1= Notification("Receive Transaction","You have received 1000 USD from Asmaa Dosuky 1234 xxx","12 Jul 2024 09:00 PM")
                    val n2= Notification("Receive Transaction","You have received 1000 USD from Asmaa Dosuky 1234 xxx","12 Jul 2024 09:00 PM")
                    val n3= Notification("Receive Transaction","You have received 1000 USD from Asmaa Dosuky 1234 xxx","12 Jul 2024 09:00 PM")
                    val notifications= listOf(n1,n2,n3)
                    NotificationPage(notifications)
                }
            }
        }
    }
}
