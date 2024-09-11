package com.example.speedotransfer.network.api

import kotlinx.coroutines.delay

interface MockApi {
    suspend fun getUsername(): String
}

class MockApiImpl : MockApi {
    override suspend fun getUsername(): String {
        delay(1000)
        return "Ahmed Ehab"
    }
}
