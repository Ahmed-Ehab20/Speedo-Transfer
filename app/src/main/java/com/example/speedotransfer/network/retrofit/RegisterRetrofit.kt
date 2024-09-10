package com.example.speedotransfer.network.retrofit

import com.example.speedotransfer.network.datamodel.LoginRequest
import com.example.speedotransfer.network.datamodel.LoginResponse
import com.example.speedotransfer.network.datamodel.RegisterRequest
import com.example.speedotransfer.network.datamodel.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun register(
    username: String,
    email: String,
    country: String,
    password: String,
    confirmPassword: String,
    dateOfBirth: String,
    onResult: (String) -> Unit
) {
    val registerRequest =
        RegisterRequest(username, email, country, password, confirmPassword, dateOfBirth)

    RetrofitInstance.api.register(registerRequest).enqueue(object : Callback<RegisterResponse> {
        override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
            if (response.isSuccessful) {
                val registerResponse = response.body()
                if (registerResponse?.status == "ACCEPTED") {
                    onResult("register Successful")
                } else {
                    onResult("register failed: ${registerResponse?.message}")
                }
            } else {
                onResult("register failed: Unknown error")
            }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            onResult("Login failed: ${t.message}")
        }
    }
    )
}
