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
import com.example.speedotransfer.model.Transaction
import com.example.speedotransfer.ui.pages.HomePage
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                    val t1 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t2 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t3 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t4 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t5 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t6 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t7 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t8 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val t9 = Transaction("Ahmed Mohamed","50000","12/30/24 11:00","Received","Visa . Mater Card . 1234", isCard = true, isSuccessful = true)
                    val transactions= listOf(t1,t2,t3,t4,t5,t6,t7,t8,t9)
                    HomePage(name = "Kirollos Luka", "10000", "EGP",transactions)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpeedoTransferTheme {
        Greeting("Android")
    }
}