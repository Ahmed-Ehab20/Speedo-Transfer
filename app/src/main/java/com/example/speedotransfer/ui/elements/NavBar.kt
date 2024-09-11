package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.ui.theme.Primary300

data class NavItem(val label: String, val iconRes: Int)

@Composable
fun BottomNavigationBar(navController: NavController, selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val navItems = listOf(
        NavItem("Home", R.drawable.home),
        NavItem("Transfer", R.drawable.transfer),
        NavItem("Transactions", R.drawable.history1),
        NavItem("My Cards", R.drawable.cards),
        NavItem("More", R.drawable.more)
    )
    val navLocations = listOf(
        Route.HOME,
        Route.TRANSFER_SCREEN,
        Route.TRANSACTIONS,
        Route.MY_CARDS, // Updated to navigate to MyCardsScreen
        Route.MORE
    )

    Surface(
        color = Color.White,
        tonalElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        modifier = Modifier.height(80.dp)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier.height(80.dp)
        ) {
            navItems.forEachIndexed { index, navItem ->
                val itemWeight = when (navItem.label) {
                    "Transactions" -> 1.5f
                    "My Cards", "Transfer" -> 1.3f
                    else -> 0.9f
                }

                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = navItem.iconRes),
                            contentDescription = navItem.label,
                            modifier = Modifier.size(24.dp),
                            tint = if (selectedItem == index) Primary300 else Color.Gray // Apply tint for selected state
                        )
                    },
                    label = {
                        Text(
                            text = navItem.label,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = if (selectedItem == index) Primary300 else Color.Gray // Apply color for selected state
                        )
                    },
                    selected = selectedItem == index,
                    onClick = {
                        onItemSelected(index)
                        // Prevent multiple copies of the same destination in the back stack
                        navController.navigate(navLocations[index]) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Primary300,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Primary300,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.weight(itemWeight)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    var selectedIndex by remember { mutableStateOf(0) }

    // Provide a mock NavController for preview
    BottomNavigationBar(
        navController = androidx.navigation.compose.rememberNavController(),
        selectedItem = selectedIndex,
        onItemSelected = { index -> selectedIndex = index }
    )
}
