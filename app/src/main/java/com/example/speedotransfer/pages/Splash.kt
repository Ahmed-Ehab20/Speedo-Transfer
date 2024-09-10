package com.example.speedotransfer.pages
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val backgroundColor = Color(0xFF871E35)

    LaunchedEffect(Unit) {
        delay(2000L)
        navController.navigate("SignIn")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Speedo Transfer",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.inter_24pt_bold)),
            color = Color.White,
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())

}