package com.moali.eqraa.core.shared.utils

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatchers {
    val io:CoroutineDispatcher
    val main:CoroutineDispatcher
}


expect fun provideDisPatchers(): Dispatchers