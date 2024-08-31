package com.example.speedotransfer.ui.pages

import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.Gray700
import com.example.speedotransfer.ui.theme.PinkGradientEnd
import com.example.speedotransfer.ui.theme.Primary300
import com.example.speedotransfer.ui.theme.YellowGradientStart
import com.google.android.ads.mediationtestsuite.dataobjects.Country
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpCountryAndDate(modifier: Modifier = Modifier) {
    var textValue by remember { mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf<CountryItem?>(null) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    var selectedDate by remember { mutableStateOf("") }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // State to track if the dialog should be shown
    var showDialog by remember { mutableStateOf(false) }


    val countries = listOf(
        CountryItem("Egypt", R.drawable.ic_flag),
        CountryItem("USA", R.drawable.ic_flag),
        CountryItem("US", R.drawable.ic_flag),
        CountryItem("Germany", R.drawable.ic_flag)
    )


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
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
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                if (scaffoldState.bottomSheetState.isVisible) {
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                }
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .fillMaxWidth()
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
                    .padding(start = 10.dp, top = 8.dp, bottom = 57.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back Screen"
                    )
                }
            }

            Text(text = "Speedo Transfer ", fontSize = 24.sp, fontWeight = FontWeight.W600)

            Text(
                text = "Welcome to Banque Misr!",
                modifier = modifier.padding(top = 67.dp),
                fontWeight = FontWeight.W600,
                fontSize = 24.sp
            )

            Text(text = "Let’s Complete your Profile",
                modifier = modifier.padding(top = 16.dp),
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = colorResource(id = R.color.G700)
                )

            Text(text = "Country", fontSize = 16.sp, fontWeight = FontWeight.W400, modifier = Modifier
                .padding(top = 32.dp, bottom = 8.dp)
                .fillMaxWidth(0.9f),color= Gray700
            )
            OutlinedTextField(
                value = textValue,
                onValueChange = { /* Handle value change */ },
                
                placeholder = { Text("Select your country") },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clickable {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_drop_down),
                        contentDescription = "Dropdown Icon"
                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.W400
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

            Text(text = "Date Of Brith", fontSize = 16.sp, fontWeight = FontWeight.W400, modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth(0.9f),color= Gray700
            )

            OutlinedTextField(
                value = selectedDate,
                onValueChange = {  },
                placeholder = { Text("DD/MM/YYY") },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clickable {
                        showDialog = true // Trigger the dialog to show
                    },

                readOnly = true, // Make the field read-only
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Select Date",
                        modifier = Modifier.clickable {
                            showDialog = true // Trigger the dialog to show
                        }
                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W400
                )
            )





            SpeedoButton(label = "Continue", onClick = {  },modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(top = 32.dp))


            Row(modifier = modifier.padding(all = 16.dp),) {

                Text(text = "Already have an account? ",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W400
                )
                Text(
                    text = " Sign In",
                    modifier = Modifier.clickable {  },
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
    SignUpCountryAndDate()
}
