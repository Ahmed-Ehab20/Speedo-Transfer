package com.example.speedotransfer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.speedotransfer.network.datamodel.Favourite
import com.example.speedotransfer.network.datamodel.Notification
import com.example.speedotransfer.network.datamodel.Transaction
import com.example.speedotransfer.network.helpers.PreferenceHelper
import com.example.speedotransfer.ui.pages.*
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
    const val ONBOARDING = "onboarding"
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
    const val FAVOURITES = "favourite"
    const val MY_CARDS = "my_cards"
}

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val isOnboardingCompleted = remember { PreferenceHelper.isOnboardingCompleted(context) }
    val showAlert = remember { mutableStateOf(false) }

    NavHost(navController = navController, startDestination = Route.SPLASH) {
        composable(Route.SPLASH) {
            SplashScreen(
                navController = navController,
                isOnboardingCompleted = isOnboardingCompleted
            )
        }
        composable(Route.ONBOARDING) {
            OnBoardingPage(
                context = context,
                navigateToSignIn = {
                    navController.navigate(Route.SIGN_IN) {
                        popUpTo(Route.ONBOARDING) { inclusive = true }
                    }
                }
            )
        }
        composable(Route.SIGN_IN) { SignInScreen(navController) }
        composable(Route.SIGN_UP) { SignUp(navController) }
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
        composable(Route.HOME) {
            HomePage(navController)
        }
        composable(Route.NOTIFICATIONS) {
            val n1 = Notification("Receive Transaction", "You have received 1000 USD from Asmaa Dosuky 1234 xxx", "12 Jul 2024 09:00 PM")
            val n2 = Notification("Receive Transaction", "You have received 1000 USD from Asmaa Dosuky 1234 xxx", "12 Jul 2024 09:00 PM")
            val n3 = Notification("Receive Transaction", "You have received 1000 USD from Asmaa Dosuky 1234 xxx", "12 Jul 2024 09:00 PM")
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
        composable(
            "transfer_success/{amount}/{recipientName}/{recipientAccount}/{senderAccount}/{senderName}/{currency}/{id}",
            arguments = listOf(
                navArgument("amount") { type = NavType.StringType },
                navArgument("recipientName") { type = NavType.StringType },
                navArgument("recipientAccount") { type = NavType.StringType },
                navArgument("senderAccount") { type = NavType.StringType },
                navArgument("senderName") { type = NavType.StringType },
                navArgument("currency") { type = NavType.StringType }
                ,navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            val recipientName = backStackEntry.arguments?.getString("recipientName") ?: ""
            val recipientAccount = backStackEntry.arguments?.getString("recipientAccount") ?: ""
            val senderAccount = backStackEntry.arguments?.getString("senderAccount") ?: ""
            val senderName = backStackEntry.arguments?.getString("senderName") ?: ""
            val currency = backStackEntry.arguments?.getString("currency") ?: ""
            val id = backStackEntry.arguments?.getString("id") ?: ""
            TransferSuccessPage(
                navController = navController,
                amount = amount,
                currency = currency,
                fromName = senderName ,
                toName = recipientName,
                fromAccount = senderAccount ,
                toAccount =recipientAccount,
                id = id
            )
        }
        composable(
            "transfer_confirmation/{amount}/{recipientName}/{recipientAccount}",
            arguments = listOf(
                navArgument("amount") { type = NavType.StringType },
                navArgument("recipientName") { type = NavType.StringType },
                navArgument("recipientAccount") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val amount = backStackEntry.arguments?.getString("amount") ?: ""
            val recipientName = backStackEntry.arguments?.getString("recipientName") ?: ""
            val recipientAccount = backStackEntry.arguments?.getString("recipientAccount") ?: ""
            ConfirmationScreen(
                navController = navController,
                amount = amount,
                recipientName = recipientName,
                recipientAccount = recipientAccount
            )
        }
        composable(Route.FAVOURITES){
            FavouritePage(navController)
        }
        composable(Route.PROFILE) { ProfileScreen(navController) }
        composable(Route.PROFILE_INFO) { ProfileInformationScreen(navController) }
        composable(Route.SETTINGS) { SettingsScreen(navController) }
        composable(Route.CHANGE_PASSWORD) { ChangePasswordScreen(navController) }
        composable(Route.EDIT_PROFILE) { EditProfileScreen(navController) }
        composable(Route.MY_CARDS) { MyCardsScreen(navController) }
    }
}
