package com.example.speedotransfer.network.datamodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.network.api.TransactionsAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionsViewModel : ViewModel() {
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    init {
        getTransactions()
    }

    private fun getTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _transactions.update { TransactionsAPIService.TransactionsAPI.getTransactions().content }
            }
            catch (e: Exception){
                Log.e("Error fetching Transactions", e.toString())
            }
        }
    }
}