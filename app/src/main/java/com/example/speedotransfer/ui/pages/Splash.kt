package com.example.speedotransfer.ui.pages
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.R
import com.example.speedotransfer.navigation.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, isOnboardingCompleted: Boolean) {
    val backgroundColor = Color(0xFF871E35)

    LaunchedEffect(Unit) {
        // Delay to show splash screen for a bit
        delay(500L)

        if (isOnboardingCompleted) {
            navController.navigate(Route.SIGN_IN) {
                // Clear splash from the back stack
                popUpTo(Route.SPLASH) { inclusive = true }
            }
        } else {
            navController.navigate(Route.ONBOARDING) {
                // Clear splash from the back stack
                popUpTo(Route.SPLASH) { inclusive = true }
            }
        }
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
            color = Color.White,
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SplashScreenPreview() {
    //SplashScreen(rememberNavController())

}