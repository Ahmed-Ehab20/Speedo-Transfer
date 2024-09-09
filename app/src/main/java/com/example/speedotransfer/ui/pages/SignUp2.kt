package com.example.speedotransfer.ui.pages

import android.icu.util.Calendar
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.speedotransfer.R
import com.example.speedotransfer.ViewModel.SignUpViewModel
import com.example.speedotransfer.network.datamodel.RegisterRequest
import com.example.speedotransfer.network.retrofit.register
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.YellowGradientStart
import com.google.gson.JsonObject
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SignUpCountryAndDate(modifier: Modifier = Modifier,navController: NavHostController) {
    val viewModel: SignUpViewModel = viewModel()


    var textValue by remember { mutableStateOf(viewModel.selectedCountry?.name ?: "") }
    var selectedCountry by remember { mutableStateOf<CountryItem?>(null) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var selectedDate by remember { mutableStateOf(viewModel.selectedDate) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // State to track if the dialog should be shown
    var showDialog by remember { mutableStateOf(false) }



    val isButtonEnabled = textValue.isNotEmpty() && selectedDate.isNotEmpty()



    var message by remember { mutableStateOf("") }



    val countries = listOf(
        CountryItem("Egypt", R.drawable.usa),
        CountryItem("USA", R.drawable.usa),
        CountryItem("US", R.drawable.usa),
        CountryItem("Germany", R.drawable.usa)
    )



    BottomSheetScaffold(

        modifier = Modifier.background(color = Color.Transparent),


        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            ) {

                items(countries) { country ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCountry = country
                                textValue = country.name
                                scope.launch {
                                    scaffoldState.bottomSheetState.partialExpand()
                                }
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = country.flagRes),
                            contentDescription = country.name,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = country.name)
                        Spacer(modifier = Modifier.weight(1f))
                        if (selectedCountry == country) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "Selected",
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        },
//        modifier = Modifier.pointerInput(Unit) {
//            detectTapGestures {
//                if (scaffoldState.bottomSheetState.isVisible) {
//                    scope.launch {
//                        // todo
//                       // scaffoldState.bottomSheetState.hide()
//                    }
//                }
//            }
//        }
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(
                    Brush.linearGradient(
                        0.0f to YellowGradientStart,
                        1.0f to PinkGradientEnd
                    )

                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 22.dp, top = 45.dp, bottom = 58.dp)
                    .height(30.dp)
                    .width(375.dp)
                ,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.navigate("SignUp") },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back Screen",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                }
            }

            Text(text = "Speedo Transfer ", fontSize = 24.sp, fontWeight = FontWeight.W600, lineHeight = 29.sp)

            Text(
                text = "Welcome to Banque Misr!",
                modifier = modifier
                    .padding(top = 66.dp)
//                    .width(330.dp)
                    .height(36.dp),
//                    .align(Alignment.End),
//                Alignment = Alignment.Center,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp
            )

            Text(
                text = "Let’s Complete your Profile",
                modifier = modifier.padding(top = 16.dp),
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = colorResource(id = R.color.G700)
            )

            Text(
                text = "Country",
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 24.sp,
                modifier = Modifier
                    .padding(top = 32.dp, bottom = 8.dp)
                    .fillMaxWidth(0.9f)
                    .width(99.dp)
                    .height(24.dp),
                color = Gray700
            )
            OutlinedTextField(
                shape = RoundedCornerShape(6.dp),
                value = textValue,
                onValueChange = { /* Handle value change */ },
                placeholder = { Text("Select your country") },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .border(
                        BorderStroke(1.5.dp, Color(0xFFB0AFAE)),
                        RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_drop_down),
                        contentDescription = "Dropdown Icon",
                        Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.W400,
                    lineHeight = 21.sp
                )
            )




            if (showDialog) {
                android.app.DatePickerDialog(
                    context,
                    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        // Update the selected date only if the user presses "OK"
                        selectedDate = "$dayOfMonth/${month + 1}/$year"
                        showDialog = false // Close the dialog
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).apply {
                    setOnCancelListener {
                        showDialog = false // Handle cancel action
                    }
                }.show()
            }

            Text(
                text = "Date Of Brith",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth(0.9f)
                    .width(99.dp)
                    .height(24.dp),
                color = Gray700
            )

            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                placeholder = { Text("DD/MM/YYY") },
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .border(
                        BorderStroke(1.5.dp, Color(0xFFB0AFAE)),
                        RoundedCornerShape(6.dp)
                    )
                    .clickable {
                        showDialog = true // Trigger the dialog to show
                    },

                readOnly = true, // Make the field read-only
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Select Date",
                        modifier = Modifier
                            .clickable {
                                showDialog = true // Trigger the dialog to show
                            }
                    )
                },
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W400,
                    lineHeight = 21.sp

                )
            )





            SpeedoButton(
                label = "Continue", onClick = {

                    val username = viewModel.username
                    val email = viewModel.email
                    val password = viewModel.password
                    val confirmPassword = viewModel.confirmPassword
                    val selectedCountry = viewModel.selectedCountry?.name ?: ""
                    val dateOfBirth = viewModel.selectedDate

                    // Call your API here with these values
                    register(username, email, selectedCountry, password, confirmPassword, dateOfBirth) { responseMessage ->
                        // Handle API response
                        Toast.makeText(context, responseMessage, Toast.LENGTH_LONG).show()
                    }


                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = 32.dp),enabled = isButtonEnabled
            )


            Row(modifier = modifier.padding(all = 16.dp)) {

                Text(
                    text = "Already have an account? ",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W400
                )
                Text(
                    text = " Sign In",
                    modifier = Modifier.clickable { navController.navigate("SignIn") },
                    textDecoration = TextDecoration.Underline,
                    color = Primary300,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500

                )

            }


        }


    }
}


data class CountryItem(val name: String, val flagRes: Int)

@Preview(showSystemUi = true)
@Composable
fun SignUpCountryAndDatePreview() {
//    SignUpCountryAndDate()
}
