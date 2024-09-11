package com.example.speedotransfer.model

import com.google.gson.annotations.SerializedName

data class AccountBalanceInfo(
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("accountNumber")
    val accountNumber: String,

)

data class UserAccountsResponse(
    @SerializedName("accounts")
    val accounts: List<AccountBalanceInfo>,
    @SerializedName("name")
    val accountName: String
)