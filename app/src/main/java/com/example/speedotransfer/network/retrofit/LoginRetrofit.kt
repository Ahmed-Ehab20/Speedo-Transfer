package com.example.speedotransfer.network.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.example.speedotransfer.network.datamodel.LoginRequest
import com.example.speedotransfer.network.datamodel.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun login(
    context: Context,
    email: String,
    password: String,
    onResult: (String, Int?, String?) -> Unit
) {
    val loginRequest = LoginRequest(email, password)

    RetrofitInstance.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                val loginResponse = response.body()
                saveToSharedPreferences(context, "token", loginResponse?.token.toString())

                val message = loginResponse?.message ?: "Unknown error"
                val userId = loginResponse?.id
                val status = loginResponse?.status

                if (status == "ACCEPTED") {
                    onResult(message, userId, status)
                } else {
                    onResult("Login failed: $message", null, status)
                }
            } else {
                onResult("Login failed: Unknown error", null, null)
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            onResult("Login failed: ${t.message}", null, null)
        }
    })
}

fun saveToSharedPreferences(context: Context, key: String, value: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
}
