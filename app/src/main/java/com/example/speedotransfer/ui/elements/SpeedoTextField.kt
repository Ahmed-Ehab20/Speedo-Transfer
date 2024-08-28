package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
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
    title: String, label: String, icon: Int=R.drawable.ic_launcher_background,
    textValue: String, onTextChange: (String) -> Unit,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier
) {
    var isShown by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(text = title, fontSize = 16.sp, modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),color= Gray700)
        OutlinedTextField(
            value = textValue,
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Gray70, unfocusedBorderColor = Gray70),
            onValueChange = onTextChange,
            trailingIcon  = {
                Box(modifier = modifier.padding(end=16.dp)) {
                    if (isPassword) {
                        if (!isShown) {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_off),
                                contentDescription = title,
                                modifier = modifier
                                    .size(24.dp)
                                    .clickable { isShown = !isShown }
                                    ,
                                tint = Gray70
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_on),
                                contentDescription = title,
                                modifier = modifier
                                    .size(24.dp)
                                    .clickable { isShown = !isShown },
                                tint = Gray70
                            )
                        }

                    } else {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = title,
                            modifier = modifier.size(24.dp),
                            tint = Gray70
                        )
                    }
                }
            },
            placeholder = { Text(text = label, color = Gray70, fontSize = 14.sp) },
            modifier = modifier.fillMaxWidth(),
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
    SpeedoTextField(title = "Title", label = "Label",
        icon = R.drawable.user, textValue = textValue, isPassword = true, onTextChange = {textValue=it})
}