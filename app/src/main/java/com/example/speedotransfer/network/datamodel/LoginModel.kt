package com.example.speedotransfer.network.datamodel


data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val username: String,
    val message: String,
    val status: String,
    val id:Int
)
