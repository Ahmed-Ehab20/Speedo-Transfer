package com.example.speedotransfer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.navigation.NavGraph
import com.example.speedotransfer.utils.InactivityDetector
import com.example.speedotransfer.navigation.Route

class MainActivity : ComponentActivity() {
    private lateinit var inactivityDetector: InactivityDetector
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inactivityDetector = InactivityDetector {
            // Handle inactivity
            handleInactivity()
        }

        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        inactivityDetector.reset()
    }

    private fun handleInactivity() {
        // Show alert and navigate to sign in
        Toast.makeText(this, "Session expired due to inactivity.", Toast.LENGTH_LONG).show()
        navController.navigate(Route.SIGN_IN) {
            // Pop back to the root to clear other screens from the back stack
            popUpTo(Route.SPLASH) { inclusive = true }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        inactivityDetector.cancel()
    }
}
