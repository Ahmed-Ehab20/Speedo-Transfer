package com.example.speedotransfer.network.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.example.speedotransfer.network.datamodel.LoginRequest
import com.example.speedotransfer.network.datamodel.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun login(context: Context,email: String, password: String, onResult: (String) -> Unit) {
    val loginRequest = LoginRequest(email, password)

    RetrofitInstance.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
            if (response.isSuccessful) {
                val loginResponse = response.body()
                saveToSharedPreferences(context, "token", loginResponse?.token.toString())
                if (loginResponse?.status == "ACCEPTED") {
                    onResult("login Successful")
                } else {
                    onResult("Login failed: ${loginResponse?.message}")
                }
            } else {
                onResult("Login failed: Unknown error")
            }
        }

        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
            onResult("Login failed: ${t.message}")
        }
    }

    )
}
fun saveToSharedPreferences(context: Context, key: String, value: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString(key, value)
    editor.apply()
}
