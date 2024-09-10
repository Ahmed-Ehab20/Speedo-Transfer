package com.example.speedotransfer.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.api.BalanceAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BalanceViewModel : ViewModel() {
    private val _balance = MutableStateFlow<Double?>(2000.0)
    val balance: StateFlow<Double?> = _balance
    private val _currency = MutableStateFlow<String?>("EGP")
    val currency: StateFlow<String?> = _currency

    init {
        getBalance()
    }
    private fun getBalance(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val api = BalanceAPIService.balanceAPI.getBalance()
                _balance.update { api.accounts.firstOrNull()?.balance }
                _currency.update { api.accounts.firstOrNull()?.currency }
            } catch (e: Exception) {
                Log.e("Error fetching balance", e.toString())
            }
        }
    }
}