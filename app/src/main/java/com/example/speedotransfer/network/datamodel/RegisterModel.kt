package com.example.speedotransfer.network.datamodel

data class RegisterRequest(
    val username:String,
    val email:String,
    val country:String,
    val password:String,
    val confirmPassword:String,
    val dateOfBirth:String
)


data class RegisterResponse(
    val userId:Int,
    val username:String,
    val email:String,
    val createdAt:String,
    val updatedAt:String,
    val message: String,
    val status:String
)



