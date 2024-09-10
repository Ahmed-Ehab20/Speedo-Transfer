package com.example.speedotransfer.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.speedotransfer.R

val InterFontFamily = FontFamily(
    Font(R.font.inter_variablefont_opsz_wght, FontWeight.Normal),
    Font(R.font.inter_italic_variablefont_opsz_wght, FontWeight.Normal, FontStyle.Italic)
)

val customTypography = androidx.compose.material.Typography(
    defaultFontFamily = InterFontFamily
)

@Composable
fun YourAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = customTypography,
        content = content
    )
}
