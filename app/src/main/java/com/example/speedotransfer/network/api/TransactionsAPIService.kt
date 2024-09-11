package com.example.speedotransfer.network.api

import android.content.Context
import com.example.speedotransfer.MyApplication
import com.example.speedotransfer.api.AuthInterceptor
import com.example.speedotransfer.constants.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TransactionsAPIService {
        private val token= MyApplication.appContext.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE).getString("token", "") ?: ""
        private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(token)) // Add the interceptor
        .build()

    private val retrofit= Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client).addConverterFactory(
        GsonConverterFactory.create()).build()
    val TransactionsAPI: TransactionsAPICallable = retrofit.create(TransactionsAPICallable::class.java)

}