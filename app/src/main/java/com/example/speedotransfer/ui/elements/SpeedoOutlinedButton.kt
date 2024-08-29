package com.example.speedotransfer.ui.elements

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.ui.theme.Primary300

@Composable
fun SpeedoOutlinedButton(
    label:String,
    onClick: () -> Unit, // Parameter to accept the click action
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.5.dp, Primary300),
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Primary300),
        shape = RoundedCornerShape(6.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 15.dp)
    ) {
        Text(text = label, fontSize = 16.sp, fontWeight = FontWeight(500))
    }
}

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0x00000000)
@Composable
private fun SpeedoOutlinedButtonPreview() {
    SpeedoOutlinedButton(label = "Sign up", onClick = { /*TODO*/ })
}