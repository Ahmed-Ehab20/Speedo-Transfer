package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.ui.theme.Primary300

@Composable
fun SpeedoButton(
    label: String,
    onClick: () -> Unit,
    backgroundColor: Color = Primary300,
    textColor: Color = Color.White,
    borderColor: Color = Color.Transparent,
    modifier: Modifier = Modifier // Add modifier to allow customization
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(6.dp)),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 15.dp)
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SpeedoButtonPreview() {
    SpeedoButton(
        label = "Previous",
        onClick = { /* Do something */ },
        backgroundColor = Color.Transparent,
        textColor = Primary300,
        borderColor = Primary300
    )
}
