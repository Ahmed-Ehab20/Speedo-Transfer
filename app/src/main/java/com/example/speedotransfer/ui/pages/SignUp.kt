package com.example.speedotransfer.ui.pages

import android.graphics.fonts.Font
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.speedotransfer.R
import com.example.speedotransfer.ViewModel.SignUpViewModel
import com.example.speedotransfer.navigation.Route
import com.example.speedotransfer.network.retrofit.login
import com.example.speedotransfer.network.retrofit.register
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.YellowGradientStart



fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}



@Composable
fun SignUp(navController: NavHostController,modifier: Modifier = Modifier) {

    val viewModel: SignUpViewModel = viewModel()

    var fullName by viewModel.fullName
    var email by viewModel.email
    var password by viewModel.password
    var confirmPassword by viewModel.confirmPassword




//    val fullName by remember { mutableStateOf(viewModel.fullName) }


//    var fullName by rememberSaveable { mutableStateOf("") }
//    var email by rememberSaveable { mutableStateOf("") }
//    var password by rememberSaveable { mutableStateOf("") }
//    var confirmPassword by rememberSaveable { mutableStateOf("") }





    val isButtonEnabled =
        fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() &&
                confirmPassword.isNotEmpty() && password.length >= 6 &&
                isEmailValid(email) && password == confirmPassword && fullName.length >= 4 &&
                !fullName.matches(".*\\d.*".toRegex())






    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(
                Brush.linearGradient(
                    0.0f to YellowGradientStart,
                    1.0f to PinkGradientEnd
                )
            )
            .verticalScroll(rememberScrollState()), // Enable vertical scrolling
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Sign Up", modifier = modifier.padding(top = 8.dp, bottom = 55.dp),
            fontSize = 20.sp, fontWeight = FontWeight.W500,  lineHeight = 30.sp
        )
        Text(
            text = "Speedo Transfer ",
            modifier = modifier.padding(bottom = 41.dp),
            fontWeight = FontWeight.W500,
            fontSize = 30.sp
        )


        SpeedoTextField(
            title = "Full Name",
            label = "Enter your Full Name",
            icon = R.drawable.user,
            textValue = fullName,
            isPassword = false,
            onTextChange = {viewModel.fullName.value = it},
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 8.dp),

        )

        
        
        
        

        SpeedoTextField(
            title = "Email",
            label = "Enter your email address",
            icon = R.drawable.email,
            textValue = email,
            isPassword = false,
            onTextChange = { viewModel.email.value = it },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 8.dp),
        )

        SpeedoTextField(
            title = "Password",
            label = "Enter your Password",
            icon = R.drawable.email,
            textValue = password,
            isPassword = true,
            onTextChange = { viewModel.password.value = it },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 8.dp),
            type = KeyboardType.Email
        )
        SpeedoTextField(
            title = "Confirm Password",
            label = "Enter your Password",
            icon = R.drawable.email,
            textValue = confirmPassword,
            isPassword = true,
            onTextChange = { viewModel.confirmPassword.value = it },
            modifier = Modifier.fillMaxWidth(0.9f),
        )
        SpeedoButton(
            label = "Sign up", onClick = {
//                register(fullName, email,password,confirmPassword) { responseMessage ->
//                    message = responseMessage
//                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
//
//                }
                navController.navigate("SignUpCountryAndDate/$fullName/$email/$password/$confirmPassword")



                }, modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 32.dp),
            enabled = isButtonEnabled
        )
        Row(modifier = modifier.padding(all = 8.dp)) {

            Text(
                text = "Already have an account? ",
                fontSize = 16.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )
            Text(
                text = " Sign In",
                modifier = Modifier.clickable { navController.navigate(Route.SIGN_IN) },
                textDecoration = TextDecoration.Underline,
                color = Primary300,
                fontSize = 16.sp,
                lineHeight = 20.sp
            )

        }
        Log.d("Navigation", "Navigating to SignUp with: fullName=$fullName, email=$email, password=$password, confirmPassword=$confirmPassword")

    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpPreview() {
//    SignUp()
}