package com.example.speedotransfer.network.datamodel


data class UpdateUserRequest(
    val username: String,
    val email: String,
    val country: String,

)

data class UpdateUserResponse(
    val message: String
)

data class UpdatePassword(
    val currentPassword:String,
    val newPassword:String
)