package com.example.speedotransfer.model

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.api.BalanceAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BalanceViewModel : ViewModel() {
    private val _balance = MutableStateFlow<Double?>(0.0)
    val balance: StateFlow<Double?> = _balance
    private val _currency = MutableStateFlow<String?>("EGP")
    val currency: StateFlow<String?> = _currency
    private val _name = MutableStateFlow<String?>("")
    val name: StateFlow<String?> = _name
    private val _accountNumber = MutableStateFlow<String?>("123456789")
    val accountNumber: StateFlow<String?> = _accountNumber
    private val _id= MutableStateFlow<String?>("0")
    val id: StateFlow<String?> = _id

    init {
        getBalance()
    }
    private fun getBalance(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = BalanceAPIService.balanceAPI.getBalance()
                _balance.update { api.accounts.firstOrNull()?.balance }
                _currency.update { api.accounts.firstOrNull()?.currency }
                _name.update { api.accountName }
                _accountNumber.update { api.accounts.firstOrNull()?.accountNumber }
                _id.update{api.id}
            } catch (e: Exception) {
                Log.e("Error fetching balance", e.toString())
            }
        }
    }
}