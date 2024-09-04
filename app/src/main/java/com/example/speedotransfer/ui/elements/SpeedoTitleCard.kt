package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R

@Composable
fun SpeedoTitleCard(title: String,modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(0.9f).padding(top=78.dp),
        verticalAlignment = Alignment.CenterVertically // Align items vertically center
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "Back"
        )
        Spacer(modifier = Modifier.weight(1f)) // Pushes the text to the center
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.align(Alignment.CenterVertically).padding(end=24.dp) // Align text vertically center
        )
        Spacer(modifier = Modifier.weight(1f)) // Ensures the text remains centered
    }
}