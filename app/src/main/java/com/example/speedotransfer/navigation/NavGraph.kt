package com.example.speedotransfer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.speedotransfer.screens.ChangePasswordScreen
import com.example.speedotransfer.screens.EditProfileScreen
import com.example.speedotransfer.screens.ProfileScreen
import com.example.speedotransfer.screens.ProfileInformationScreen
import com.example.speedotransfer.screens.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "profile") {
        composable("profile") { ProfileScreen(navController) }
        composable("profile_info") { ProfileInformationScreen(navController) }
        composable("setting") { SettingsScreen(navController) }
        composable("change_password") { ChangePasswordScreen(navController) }
        composable("edit_profile") { EditProfileScreen(navController) }
    }
}
