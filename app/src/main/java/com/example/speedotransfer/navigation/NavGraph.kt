package com.example.speedotransfer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.speedotransfer.network.datamodel.Favourite
import com.example.speedotransfer.network.datamodel.Notification
import com.example.speedotransfer.ui.pages.ChangePasswordScreen
import com.example.speedotransfer.ui.pages.ConfirmationScreen
import com.example.speedotransfer.ui.pages.EditProfileScreen
import com.example.speedotransfer.ui.pages.FavouritePage
import com.example.speedotransfer.ui.pages.HomePage
import com.example.speedotransfer.ui.pages.LastTransactionsPage
import com.example.speedotransfer.ui.pages.MorePage
import com.example.speedotransfer.ui.pages.NotificationPage
import com.example.speedotransfer.ui.pages.ProfileScreen
import com.example.speedotransfer.ui.pages.ProfileInformationScreen
import com.example.speedotransfer.ui.pages.SettingsScreen
import com.example.speedotransfer.ui.pages.SignInScreen
import com.example.speedotransfer.ui.pages.SignUp
import com.example.speedotransfer.ui.pages.SignUpCountryAndDate
import com.example.speedotransfer.ui.pages.SplashScreen
import com.example.speedotransfer.ui.pages.TransferScreen
import com.example.speedotransfer.ui.pages.TransferSuccessPage
import com.example.speedotransfer.ui.pages.ViewTransactionPage

object Route {
    const val SPLASH = "splash"
    const val SIGN_IN = "sign_in"
    const val SIGN_UP = "sign_up"
    const val HOME = "home"
    const val PROFILE = "profile"
    const val PROFILE_INFO = "profile_info"
    const val SETTINGS = "setting"
    const val CHANGE_PASSWORD = "change_password"
    const val EDIT_PROFILE = "edit_profile"
    const val NOTIFICATIONS = "notifications"
    const val TRANSACTIONS = "transactions"
    const val VIEW_TRANSACTION = "view_transaction"
    const val TRANSFER_SUCCESS = "transfer_success"
    const val TRANSFER_SCREEN = "transfer_screen"
    const val TRANSFER_CONFIRMATION="transfer_confirmation"
    const val MORE = "more"
    const val FAVOURITES="favourite"
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable(Route.HOME) {
            HomePage(navController)
        }
        composable(Route.NOTIFICATIONS) {
            val n1 = Notification(
                "Receive Transaction",
                "You have received 1000 USD from Asmaa Dosuky 1234 xxx",
                "12 Jul 2024 09:00 PM"
            )
            val n2 = Notification(
                "Receive Transaction",
                "You have received 1000 USD from Asmaa Dosuky 1234 xxx",
                "12 Jul 2024 09:00 PM"
            )
            val n3 = Notification(
                "Receive Transaction",
                "You have received 1000 USD from Asmaa Dosuky 1234 xxx",
                "12 Jul 2024 09:00 PM"
            )
            val notifications = listOf(n1, n2, n3)
            NotificationPage(navController, notifications)
        }
        composable(Route.TRANSACTIONS) {
            LastTransactionsPage(navController)
        }
        composable("${Route.VIEW_TRANSACTION}/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            ViewTransactionPage(
                navController = navController,
                id = id // Pass the id parameter here
            )
        }
        composable(Route.MORE) {
            MorePage(navController)
        }
        composable(Route.TRANSFER_SCREEN) {
            TransferScreen(navController)
        }
        composable(Route.TRANSFER_SUCCESS) {
            TransferSuccessPage(
                navController,
                amount = "1000",
                currency = "EGP",
                fromName = "Asmaa Dosuky",
                toName = "Jonathon Smith",
                fromAccount = "Account xxxx7890",
                toAccount = "Account xxxx7890"
            )
        }
        composable(Route.TRANSFER_CONFIRMATION){
            ConfirmationScreen(
                navController,
                amount = "1000",
                fromName = "Asmaa Dosuky",
                recipientName = "Jonathon Smith",
                fromAccount = "Account xxxx7890",
                recipientAccount = "Account xxxx7890"
            )
        }
        composable(Route.FAVOURITES){
            val f1 = Favourite("Asmaa Dosuky", "Account xxxx7890")
            val f2 = Favourite("Asmaa Dosuky", "Account xxxx7890")
            val favourites = listOf(f1, f2)

            FavouritePage(navController,favourites)
        }
        composable(Route.PROFILE) { ProfileScreen(navController) }
        composable(Route.PROFILE_INFO) { ProfileInformationScreen(navController) }
        composable(Route.SETTINGS) { SettingsScreen(navController) }
        composable(Route.CHANGE_PASSWORD) { ChangePasswordScreen(navController) }
        composable(Route.EDIT_PROFILE) { EditProfileScreen(navController) }


//        composable("edit_profile/{userId}") { backStackEntry ->
//            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
//            if (userId != null) {
//                EditProfileScreen(navController = navController, userId = userId)
//            } else {
//            }
//        }


        composable(Route.SPLASH) { SplashScreen(navController = navController) }
        composable(Route.SIGN_IN) { SignInScreen(navController = navController) }
        composable(Route.SIGN_UP) { SignUp(navController = navController) }
//        composable("SignUpCountryAndDate") { SignUpCountryAndDate(navController = navController) }

        composable("SignUpCountryAndDate/{fullName}/{email}/{password}/{confirmPassword}") { backStackEntry ->
            val fullName = backStackEntry.arguments?.getString("fullName")
            val email = backStackEntry.arguments?.getString("email")
            val password = backStackEntry.arguments?.getString("password")
            val confirmPassword = backStackEntry.arguments?.getString("confirmPassword")

            SignUpCountryAndDate(
                navController = navController,
                fullName = fullName,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )



        }
    }
}
