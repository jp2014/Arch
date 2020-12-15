package com.example.arch.repo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class FakeMainRepo @Inject constructor() : MainRepo {
    override suspend fun getSomething(): Flow<String> {
        return flowOf("yo, this is something").onStart { delay(5000) }
    }

}