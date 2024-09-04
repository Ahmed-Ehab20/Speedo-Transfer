package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.ui.elements.SpeedoMore
import com.example.speedotransfer.ui.elements.SpeedoTitleCard
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun MorePage(modifier: Modifier = Modifier) {
    Column(modifier=Modifier.fillMaxSize().background(Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd)), horizontalAlignment = Alignment.CenterHorizontally) {
        SpeedoTitleCard(title = "More")
        SpeedoMore(icon = R.drawable.website, text = "Transfer From Website",modifier=Modifier.padding(top=24.dp).fillMaxWidth(0.9f))
        SpeedoMore(icon = R.drawable.favorite, text = "Favourites",modifier=Modifier.fillMaxWidth(0.9f))
        SpeedoMore(icon = R.drawable.person, text = "Profile",modifier=Modifier.fillMaxWidth(0.9f))
        SpeedoMore(icon = R.drawable.danger, text = "Help",modifier=Modifier.fillMaxWidth(0.9f))
        SpeedoMore(icon = R.drawable.logout, text = "Logout", isFinal = true,modifier=Modifier.fillMaxWidth(0.9f))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun MorePagePreview() {
    MorePage()
}