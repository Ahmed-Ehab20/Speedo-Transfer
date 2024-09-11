package com.example.speedotransfer.network.api

import com.example.speedotransfer.network.datamodel.TransferRequest
import com.example.speedotransfer.network.datamodel.TransferResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TransferAPICallable {
    @POST("/transfer")
    suspend fun transferMoney(
        @Body transferRequest: TransferRequest
    ): TransferResponse

}