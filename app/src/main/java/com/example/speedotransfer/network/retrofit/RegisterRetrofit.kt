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
                if (registerResponse != null && registerResponse.username == username) {
                    onResult("Registration Successful ${registerResponse.status}")
                } else {
                    val errorMessage = registerResponse?.status ?: "Unknown error"
                    onResult("Registration failed: $errorMessage")
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                onResult("Registration failed: $errorBody")
            }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
            onResult("Registration failed: ${t.message}")
        }
    })
}
