package com.example.speedotransfer.api

import com.example.speedotransfer.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BalanceAPIService {
    private var token: String = ""

    fun setToken(newToken: String) {
        token = newToken
    }

    // Create a client with the interceptor using the stored token
    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val balanceAPI: BalanceAPICallable = retrofit.create(BalanceAPICallable::class.java)
}
