package com.moali.eqraa.core.shared.utils

import kotlinx.coroutines.CoroutineDispatcher


class IOSDispatchers: Dispatchers {
    override val io: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.Default
    override val main: CoroutineDispatcher
        get() =kotlinx.coroutines.Dispatchers.Default

}

actual fun provideDisPatchers(): Dispatchers = IOSDispatchers()