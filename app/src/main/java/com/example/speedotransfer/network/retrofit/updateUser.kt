package com.example.speedotransfer.network.retrofit


import com.example.speedotransfer.network.datamodel.LoginResponse
import com.example.speedotransfer.network.datamodel.UpdateUserRequest
import com.example.speedotransfer.network.datamodel.UpdateUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun updateUser(
    userId: Int,
    username: String,
    email: String,
    country: String,
    resultMessage: (String) -> Unit
) {
    val updateRequest = UpdateUserRequest(username, email, country)

    RetrofitInstance.api.updateUserById(userId, updateRequest)
        .enqueue(object : Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                if (response.isSuccessful) {
                    resultMessage(response.body()?.message ?: "Update successful")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    val errorMessage = parseErrorMessage(errorBody)
                    resultMessage("update failed: $errorMessage")                }
            }

            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                resultMessage("Failure: ${t.message}")
            }
        })
}

private fun parseErrorMessage(errorBody: String): String {
    return errorBody
}
