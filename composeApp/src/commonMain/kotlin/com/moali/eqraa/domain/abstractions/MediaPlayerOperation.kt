package com.moali.eqraa.domain.abstractions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface MediaPlayerOperation {

    val current:MutableStateFlow<Int?>
    fun prepare(pathSource: String, listener: MediaPlayerListener)

    fun start()

    fun pause()

    fun stop()

    fun isPlaying(): Boolean

    fun release()

    fun getDuration():Int?

    fun getCurrentPosition():Flow<Int?>

    fun navigateTo(duration: Int)

    fun skip10Sec()

    fun back10Sec()

    fun setSpeed(speed: Float)

}