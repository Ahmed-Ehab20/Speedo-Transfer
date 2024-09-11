package com.example.speedotransfer.network.datamodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.network.api.TransferAPICallable
import com.example.speedotransfer.network.api.TransferAPIService
import com.example.speedotransfer.network.datamodel.TransferResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TransferViewModel : ViewModel() {

    // To store transfer status
    private val _transferStatus = MutableStateFlow<TransferResponse?>(null)
    val transferStatus: StateFlow<TransferResponse?> = _transferStatus

    fun transferMoney(transferRequest: TransferRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = TransferAPIService.transferAPI.transferMoney(transferRequest)
                _transferStatus.update{response}
            } catch (e: HttpException) {
                Log.e("Error in Transfer", e.toString())
                if(e.code()==404)
                    _transferStatus.update{TransferResponse(success = false, message = "Account number not found")}
            }
        }
    }
}
