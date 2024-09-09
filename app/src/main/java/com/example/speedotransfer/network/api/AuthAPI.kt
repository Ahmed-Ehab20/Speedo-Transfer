package com.example.speedotransfer.network.api

import com.example.speedotransfer.network.datamodel.LoginRequest
import com.example.speedotransfer.network.datamodel.LoginResponse
import com.example.speedotransfer.network.datamodel.RegisterRequest
import com.example.speedotransfer.network.datamodel.RegisterResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}