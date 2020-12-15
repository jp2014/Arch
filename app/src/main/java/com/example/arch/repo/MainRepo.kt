package com.example.arch.repo

import kotlinx.coroutines.flow.Flow

interface MainRepo {
    suspend fun getSomething(): Flow<String>
}