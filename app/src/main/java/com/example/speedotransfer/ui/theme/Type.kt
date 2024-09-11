package com.example.speedotransfer.ui.theme

import androidx.compose.material3.Typography
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

val customTypography = Typography(
    // Define the typography properties you need, like bodyLarge, titleLarge, etc.
    bodyLarge = Typography().bodyLarge.copy(fontFamily = InterFontFamily),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = InterFontFamily),
    bodySmall = Typography().bodySmall.copy(fontFamily = InterFontFamily),
    titleLarge = Typography().titleLarge.copy(fontFamily = InterFontFamily),
    titleMedium = Typography().titleMedium.copy(fontFamily = InterFontFamily),
    titleSmall = Typography().titleSmall.copy(fontFamily = InterFontFamily),
    labelLarge = Typography().labelLarge.copy(fontFamily = InterFontFamily),
    labelMedium = Typography().labelMedium.copy(fontFamily = InterFontFamily),
    labelSmall = Typography().labelSmall.copy(fontFamily = InterFontFamily),
)

@Composable
fun YourAppTheme(
    content: @Composable () -> Unit
) {
    androidx.compose.material3.MaterialTheme(
        typography = customTypography,
        content = content
    )
}
