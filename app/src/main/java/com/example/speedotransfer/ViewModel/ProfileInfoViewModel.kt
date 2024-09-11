package com.example.speedotransfer.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.network.api.UsersAPIService
import com.example.speedotransfer.network.datamodel.ProfileInfo
import com.example.speedotransfer.network.datamodel.UserPreferences
import com.example.speedotransfer.network.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ProfileInfoViewModel() : ViewModel() {



    private val _profileInfo = MutableStateFlow(ProfileInfo("", "", "", listOf()))
    val profileInfo: StateFlow<ProfileInfo> = _profileInfo

    init {
        fetchProfileInformation()
    }

    fun fetchProfileInformation() {
        viewModelScope.launch {
            try {
                val info = UsersAPIService.balanceAPI.getProfileInformation()

                val response = RetrofitInstance.api.getProfileInformation()
                UsersAPIService.balanceAPI.getProfileInformation()
                _profileInfo.update { ProfileInfo(info.name,info.email,info.country,info.accounts) }
            } catch (e: Exception) {
                // Handle error appropriately
                _profileInfo.value = ProfileInfo("", "", "", listOf())
            }
        }
    }
}
