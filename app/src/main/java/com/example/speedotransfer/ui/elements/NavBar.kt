package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.example.speedotransfer.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.speedotransfer.ui.theme.Primary300

data class NavItem(val label: String, val iconRes: Int)

@Composable
fun BottomNavigationBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val navItems = listOf(
        NavItem("Home", R.drawable.home),
        NavItem("Transfer", R.drawable.transfer),
        NavItem("Transactions", R.drawable.history1),
        NavItem("My cards", R.drawable.cards),
        NavItem("More", R.drawable.more)
    )

    Surface(
        color = Color.White,
        elevation = 8.dp,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier.height(80.dp)
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.height(80.dp)
        ) {
            navItems.forEachIndexed { index, navItem ->
                val itemWeight = if (navItem.label == "Transactions") 1.5f else if (navItem.label == "My cards" || navItem.label == "Transfer") 1.3f else 0.9f

                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = navItem.iconRes),
                            contentDescription = navItem.label,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = navItem.label,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index) },
                    selectedContentColor = Primary300,
                    unselectedContentColor = Color.Gray,
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

    BottomNavigationBar(
        selectedItem = selectedIndex,
        onItemSelected = { index -> selectedIndex = index }
    )
}