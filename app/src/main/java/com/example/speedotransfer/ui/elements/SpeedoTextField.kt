package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Gray70
import com.example.speedotransfer.ui.theme.Gray700


@Composable
fun SpeedoTextField(
    title: String,
    label: String,
    icon: Int = R.drawable.ic_launcher_background,
    textValue: String,
    onTextChange: (String) -> Unit,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    var isShown by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            color = Gray700
        )
        OutlinedTextField(
            value = textValue,
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Gray70, unfocusedBorderColor = Gray70),
            onValueChange = onTextChange,
            trailingIcon = {
                if (isPassword) {
                    Icon(
                        painter = painterResource(id = if (isShown) R.drawable.visibility_on else R.drawable.visibility_off),
                        contentDescription = title,
                        modifier = Modifier
                            .size(24.dp) // Use a separate modifier for size
                            .clickable { isShown = !isShown }
                            .padding(end = 8.dp), // Adjust padding as needed
                        tint = Gray70
                    )
                } else {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = title,
                        modifier = Modifier
                            .size(24.dp) // Use a separate modifier for size
                            .padding(end = 8.dp), // Adjust padding as needed
                        tint = Gray70
                    )
                }
            },
            placeholder = {
                Text(text = label, color = Gray70, fontSize = 14.sp)
            },
            modifier = modifier
                .fillMaxWidth(), // Apply the main modifier only to OutlinedTextField
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done // Adjust as needed
            ),
            singleLine = true,
            visualTransformation = if (isPassword && !isShown) {
                PasswordVisualTransformation()
            } else VisualTransformation.None,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SpeedoTextFieldPreview() {
    var textValue by remember { mutableStateOf("") }

    // Example of using the SpeedoTextField
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        SpeedoTextField(
            title = "Email",
            label = "Enter your email address",
            icon = R.drawable.ic_email, // Replace with your actual drawable resource
            textValue = textValue,
            onTextChange = { textValue = it },
            isPassword = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        SpeedoTextField(
            title = "Password",
            label = "Enter your password",
            icon = R.drawable.ic_password, // Replace with your actual drawable resource
            textValue = textValue,
            onTextChange = { textValue = it },
            isPassword = true
        )
    }
}
