package com.moali.eqraa.core.shared.utils

import kotlinx.coroutines.CoroutineDispatcher


class IOSDispatchers: Dispatchers {
    override val io: CoroutineDispatcher
        get() = kotlinx.coroutines.Dispatchers.IO
    override val main: CoroutineDispatcher
        get() =kotlinx.coroutines.Dispatchers.IO

}

actual fun provideDisPatchers(): Dispatchers = IOSDispatchers()