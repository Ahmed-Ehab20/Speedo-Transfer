package com.example.speedotransfer.network.datamodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.network.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
//
//class ProfileInformationViewModel : ViewModel() {
////     Mocking the profile information
//    private val _profileInfo = MutableStateFlow(
//        ProfileInfo(
//            name = "Ahmed Ehab",
//            email = "ahmed@gmail.com",
//            dateOfBirth = "24/05/2003",
//            country = "Egypt",
//            bankAccount = "1234xxxx"
//        )
//    )
//
//    val profileInfo: StateFlow<ProfileInfo> = _profileInfo.asStateFlow()
//
//
//
//}
//
//
//// Data class to hold the profile information
//data class ProfileInfo(
//    val name: String,
//    val email: String,
//    val dateOfBirth: String,
//    val country: String,
//    val bankAccount: String
//)

//
data class ProfileInfo(
    val name: String,
    val email: String,
    val country:String,
    val accounts:List<accounts>

)

data class accounts(
    val accountNumber:String

)
