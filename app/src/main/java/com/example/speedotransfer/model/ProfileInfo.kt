package com.example.speedotransfer.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileInformationViewModel : ViewModel() {
    // Mocking the profile information
    private val _profileInfo = MutableStateFlow(
        ProfileInfo(
            fullName = "Ahmed Ehab",
            email = "ahmed@gmail.com",
            dateOfBirth = "24/05/2003",
            country = "Egypt",
            bankAccount = "1234xxxx"
        )
    )

    val profileInfo: StateFlow<ProfileInfo> = _profileInfo.asStateFlow()
}

// Data class to hold the profile information
data class ProfileInfo(
    val fullName: String,
    val email: String,
    val dateOfBirth: String,
    val country: String,
    val bankAccount: String
)
