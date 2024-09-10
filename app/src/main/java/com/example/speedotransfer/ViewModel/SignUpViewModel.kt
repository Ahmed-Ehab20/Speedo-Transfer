package com.example.speedotransfer.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.speedotransfer.ui.pages.CountryItem

class SignUpViewModel : ViewModel() {


    var fullName: MutableState<String> = mutableStateOf("")
    var email: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    var confirmPassword: MutableState<String> = mutableStateOf("")







//    var fullName by mutableStateOf("")
//    var email:String =  ""
//    var password :String =  ""
//    var confirmPassword :String =  ""
//
//        private set
//
//    fun updateFullName(newFullName: String) {
//        fullName = newFullName
//    }
//


}