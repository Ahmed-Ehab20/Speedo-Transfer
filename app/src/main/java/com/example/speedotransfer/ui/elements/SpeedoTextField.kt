package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Danger300
import com.example.speedotransfer.ui.theme.Gray70
import com.example.speedotransfer.ui.theme.Gray700


@Composable
fun SpeedoTextField(
    title: String, label: String, icon: Int=R.drawable.ic_launcher_background,
    textValue: String, onTextChange: (String) -> Unit,
    isPassword: Boolean = false,
    modifier: Modifier = Modifier,
    errorMessage:String?=null,
    type: KeyboardType = KeyboardType.Text
) {
    var isShown by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        Text(text = title, fontSize = 16.sp, modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),color= Gray700)
        OutlinedTextField(
            value = textValue,
            colors = if(errorMessage==null) {OutlinedTextFieldDefaults.colors(focusedBorderColor = Gray700, unfocusedBorderColor = Gray70, focusedTextColor = Gray700, unfocusedTextColor = Gray700, focusedTrailingIconColor = Gray700, unfocusedTrailingIconColor = Gray70
            )} else {OutlinedTextFieldDefaults.colors(focusedBorderColor = Danger300, unfocusedBorderColor = Danger300, focusedTextColor = Danger300, unfocusedTextColor = Gray700, focusedTrailingIconColor = Danger300, unfocusedTrailingIconColor = Danger300)},
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Gray700
            )
            ,
            onValueChange = onTextChange,
            trailingIcon  = {
                Box(modifier = Modifier.padding(end=16.dp)) {
                    if (isPassword) {
                        if (!isShown) {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_off),
                                contentDescription = title,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { isShown = !isShown }
                                    ,

                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility_on),
                                contentDescription = title,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable { isShown = !isShown },
                            )
                        }

                    } else {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = title,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
            },
            placeholder = { Text(text = label, color = Gray70, fontSize = 14.sp) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done, // Adjust as needed
                keyboardType = type
            ),
            singleLine = true,
            visualTransformation = if (isPassword && !isShown) {
                PasswordVisualTransformation()
            } else VisualTransformation.None,

        )
        //TODO ensure how i should implement this, whether i should hide it or completely remove it
            if(errorMessage!=null)
            Text(text = errorMessage ?: "", fontSize = 14.sp, modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth().alpha(if (errorMessage != null) 1f else 0f),color= Danger300)


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SpeedoTextFieldPreview() {
    var textValue by remember { mutableStateOf("") }
    Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,modifier=Modifier.fillMaxSize()){
        SpeedoTextField(title = "Title", label = "Label",
            icon = R.drawable.user, textValue = textValue, isPassword = true, onTextChange = {textValue=it},modifier=Modifier.fillMaxWidth(0.9f), type = KeyboardType.Email)
        SpeedoTextField(title = "Title", label = "Label",
            icon = R.drawable.user, textValue = textValue, isPassword = true, onTextChange = {textValue=it},modifier=Modifier.fillMaxWidth(0.9f), errorMessage = "test")
    }
}