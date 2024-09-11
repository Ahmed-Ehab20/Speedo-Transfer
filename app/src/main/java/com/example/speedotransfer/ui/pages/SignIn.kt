package com.example.speedotransfer.ui.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.R
import com.example.speedotransfer.network.retrofit.login
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.elements.SpeedoButton

@Composable
fun SignInScreen(navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var message by remember { mutableStateOf("") }
    val context = LocalContext.current



    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFFFFF), Color(0xFFFFEAEE)),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign In",
            fontSize = 28.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Speedo Transfer",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 80.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        SpeedoTextField(
            title = "Email",
            label = "Enter your email address",
            icon = R.drawable.ic_email,
            textValue = email,
            onTextChange = { email = it },
            isPassword = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        SpeedoTextField(
            title = "Password",
            label = "Enter your password",
            icon = R.drawable.visibility_on,
            textValue = password,
            onTextChange = { password = it },
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        SpeedoButton(
            label = "Sign in",
            onClick = {
                login(email, password) { responseMessage ->
                    message = responseMessage
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()

                    if (message == "login Successful"){
                        navController.navigate(Route.HOME)
                    }
                }

            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))



        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Don't have an account? ",
                fontSize = 14.sp,
                color = Color.Gray
            )

            ClickableText(
                text = AnnotatedString("Sign Up"),
                onClick = { navController.navigate(Route.SIGN_UP) },
                style = TextStyle(
                    color = Primary300,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignInScreenPreview() {
//    SignInScreen()
}