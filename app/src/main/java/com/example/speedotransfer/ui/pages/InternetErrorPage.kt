package com.example.speedotransfer.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoOutlinedButton
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.YellowGradientStart

@Composable
fun InternetErrorPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Brush.linearGradient(0.0f to YellowGradientStart, 1.0f to PinkGradientEnd)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.no_internet), contentDescription = "No internet",modifier=modifier.padding(bottom=54.dp))
        Text(text="Internet connection \n disabled...",fontSize= 24.sp, fontWeight = FontWeight.W600, lineHeight = 36.sp, modifier = modifier.fillMaxWidth(0.9f).padding(bottom=32.dp), textAlign = TextAlign.Center)
        SpeedoButton(label = "Update", onClick = { /*TODO*/ }, modifier = modifier.fillMaxWidth(0.9f))
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun InternetErrorPagePreview() {
    InternetErrorPage()
}