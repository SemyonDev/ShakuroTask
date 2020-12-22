package com.semyong.shakurotask.data.helpers

import kotlinx.coroutines.flow.MutableStateFlow

class DataErrorManager {
    val throwableFlow = MutableStateFlow<Throwable?>(null)

    fun flow(throwable: Throwable) {
        throwableFlow.value = throwable
    }
}