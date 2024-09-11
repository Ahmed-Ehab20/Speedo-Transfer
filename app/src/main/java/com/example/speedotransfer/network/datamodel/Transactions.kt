package com.example.speedotransfer.network.datamodel


data class Transactions(
    val transactions: List<Transaction>
)


data class Transaction(
    val transactionId: Int,
    val amount: Double,
    val recipientName: String,
    val transactionDate: String,
    val status: String,
    val accountNumber: String
)