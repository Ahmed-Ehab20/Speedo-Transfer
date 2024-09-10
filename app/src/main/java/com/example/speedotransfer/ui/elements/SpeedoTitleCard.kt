package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.speedotransfer.R

@Composable
fun SpeedoTitleCard(
    title: String,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth().padding(top=16.dp).fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 4.dp)
                .clickable { navController?.popBackStack() }
                .padding(end = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 40.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SpeedoTitleCardPreview() {
    SpeedoTitleCard(title = "Profile Information", navController = null)
}
