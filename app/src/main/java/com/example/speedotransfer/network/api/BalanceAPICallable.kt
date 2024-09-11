package com.example.speedotransfer.api

import com.example.speedotransfer.model.UserAccountsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BalanceAPICallable {
    @GET("users/id")
    suspend fun getBalance(): UserAccountsResponse
}