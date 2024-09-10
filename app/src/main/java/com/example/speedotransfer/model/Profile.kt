package com.example.speedotransfer.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.api.MockApiImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    init {
        fetchUsername()
    }

    private fun fetchUsername() {
        viewModelScope.launch {
            val api = MockApiImpl()
            _username.value = api.getUsername()
        }
    }
}

