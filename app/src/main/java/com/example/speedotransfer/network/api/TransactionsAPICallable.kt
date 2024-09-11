package com.example.speedotransfer.network.api

import com.example.speedotransfer.network.datamodel.Transactions
import retrofit2.http.GET

interface TransactionsAPICallable {
    @GET("/transactions")
    suspend fun getTransactions(): Transactions

}