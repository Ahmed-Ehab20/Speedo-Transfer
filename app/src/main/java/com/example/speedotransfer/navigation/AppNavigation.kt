package com.example.speedotransfer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.ui.pages.SignInScreen
import com.example.speedotransfer.ui.pages.SignUp
import com.example.speedotransfer.ui.pages.SignUpCountryAndDate
import com.example.speedotransfer.ui.pages.SplashScreen


@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController = navController) }
        composable("SignIn") { SignInScreen(navController = navController) }
        composable("signup") { SignUp(navController = navController) }
        composable("SignUpCountryAndDate") { SignUpCountryAndDate(navController = navController) }

    }
}
