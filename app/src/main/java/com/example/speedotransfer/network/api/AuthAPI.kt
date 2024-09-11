package com.example.speedotransfer.network.api

import com.example.speedotransfer.network.datamodel.LoginRequest
import com.example.speedotransfer.network.datamodel.LoginResponse
import com.example.speedotransfer.network.datamodel.ProfileInfo
import com.example.speedotransfer.network.datamodel.RegisterRequest
import com.example.speedotransfer.network.datamodel.RegisterResponse
import com.example.speedotransfer.network.datamodel.UpdatePassword
import com.example.speedotransfer.network.datamodel.UpdateUserRequest
import com.example.speedotransfer.network.datamodel.UpdateUserResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthAPI {
    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<RegisterResponse>


    @PUT("users/{id}")
    fun updateUserById(
        @Path("id") userId: Int,
        @Body updateUserRequest: UpdateUserRequest
    ): Call<UpdateUserResponse>

    @PUT("users/{id}/changepassword")
    fun updatePassword(
        @Path("id") userId: Int,
        @Body updateUserRequest: UpdatePassword
    ): Call<UpdateUserResponse>

    @GET("users/id")
    suspend fun getProfileInformation(): ProfileInfo


}

//