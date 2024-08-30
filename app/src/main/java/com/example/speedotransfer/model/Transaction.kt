package com.example.speedotransfer.model

data class Transaction(
    val name: String,
    val amount: String,
    val date: String,
    val type: String,
    val cardDetails: String,
    val currency: String = "EGP",
    val isSuccessful:Boolean,
    val isCard:Boolean,
){

}
