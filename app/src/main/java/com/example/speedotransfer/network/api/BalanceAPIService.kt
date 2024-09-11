package com.example.speedotransfer.api

import com.example.speedotransfer.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BalanceAPIService {
    private const val token="eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQyLCJzdWIiOiJ0ZXN0aW5nQGdtYWlsLmNvbSIsImlhdCI6MTcyNjA0MDM1NSwiZXhwIjoxNzI2MDQzOTU1fQ.UAnZP_czbnd2dstNpMDuN8eedpDcnAR__wr-rZbPirE"
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token)) // Add the interceptor
        .build()

    private val retrofit= Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build()
    val balanceAPI: BalanceAPICallable = retrofit.create(BalanceAPICallable::class.java)

}