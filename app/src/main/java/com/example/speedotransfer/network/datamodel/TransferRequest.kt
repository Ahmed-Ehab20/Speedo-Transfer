package com.example.speedotransfer.network.datamodel

data class TransferRequest(
    var senderId: String,
    var recipientName: String,
    var recipientAccountNumber: String,
    var amount: Double,
)